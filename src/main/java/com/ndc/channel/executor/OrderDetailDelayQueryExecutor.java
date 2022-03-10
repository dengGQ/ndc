package com.ndc.channel.executor;

import com.ndc.channel.flight.handler.NdcFlightOrderDetailHandler;
import com.ndc.channel.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * NDC订单明细延迟查询
 */
@Slf4j
@Component
public class OrderDetailDelayQueryExecutor {

    public static final String orderStatusQueryKey = "order_status_query_key";

    @Resource
    private RedisUtils redisUtils;

    private static Thread th = null;

    private DelayTask delayTask = new DelayTask();

    @Resource
    private NdcFlightOrderDetailHandler orderDetailHandler;

    public void submitTask(String msgBody, long delaySecond) {

        redisUtils.zsetAddWithScore(orderStatusQueryKey, msgBody, System.currentTimeMillis() / 1000 + delaySecond);

        if (th == null || th.getState() == Thread.State.TERMINATED) {

            th = new Thread(delayTask);
            th.setDaemon(true);
            th.start();
        }
    }

    class DelayTask implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Set<ZSetOperations.TypedTuple> tupleSet = redisUtils.rangeWithScores(orderStatusQueryKey, 0, 1);

                    if (tupleSet == null || tupleSet.size() ==0) {
                        log.info("没任务休息会.............");
                        break;
                    }

                    ZSetOperations.TypedTuple typedTuple = tupleSet.stream().collect(Collectors.toList()).get(0);
                    long score = typedTuple.getScore().longValue();
                    String msgBody = typedTuple.getValue().toString();
                    long currentV = System.currentTimeMillis()/1000;

                    if ((currentV - score) >= 0) {

                        if (redisUtils.zsetRem(orderStatusQueryKey, msgBody) > 0) {

                            log.info("开始干活, msgBody={}, score={}, current={}", msgBody, score, currentV);
                            orderDetailHandler.orderStatusProcess(msgBody);
                        }
                    }else {
                        log.info("有任务，但未到工作时间或任务已被其他兄弟做了.............");
                        Thread.sleep(1000l);
                    }
                } catch (InterruptedException e) {
                    log.error("线程中断异常", e);
                } catch (Exception e) {
                    log.error("订单状态刷新异常", e);
                }
            }
        }
    }
}
