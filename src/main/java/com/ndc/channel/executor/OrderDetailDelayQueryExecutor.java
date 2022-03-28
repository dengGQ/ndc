package com.ndc.channel.executor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.flight.dto.MsgBody;
import com.ndc.channel.flight.dto.orderDetail.NdcOrderDetailData;
import com.ndc.channel.flight.handler.NdcOrderDetailHandler;
import com.ndc.channel.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
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

    @Resource
    private Map<String, NdcOrderDetailHandler> orderDetailHandlerMap;

    private static Thread th = null;

    private DelayTask delayTask = new DelayTask();

    @PostConstruct
    public void initWorker() {
        th = new Thread(delayTask);
        th.setDaemon(true);
        th.start();
    }

    public void submitTask(String msgBody, long delaySecond) {

        redisUtils.zsetAddWithScore(orderStatusQueryKey, msgBody, System.currentTimeMillis() / 1000 + delaySecond);

        if (th == null || th.getState() == Thread.State.TERMINATED) {
            initWorker();
        }
    }

    public static final String ORDER_DETAIL_HANDLER_NAME_PREFIX = "orderDetailHandler";

    public void orderStatusProcess(String msgBody) {

        MsgBody mb = JSONObject.parseObject(msgBody, MsgBody.class);
        String msgType = mb.getMsgType();
        String businessNumber = mb.getBusinessNumber();

        NdcOrderDetailHandler detailHandler = orderDetailHandlerMap.get( ORDER_DETAIL_HANDLER_NAME_PREFIX + msgType);

        try {
            NdcOrderDetailData ndcOrderDetailData = detailHandler.orderDetail(businessNumber);
            log.info("NDC订单明细查询结果={}", JSON.toJSONString(ndcOrderDetailData));
            if (detailHandler.checkStatusComplete(ndcOrderDetailData)) {

                detailHandler.statusChangeNotice(ndcOrderDetailData);
            }else {

                this.submitTask(msgBody, 60*10);
            }
        }catch (Exception e) {
            this.submitTask(msgBody, 60*10);
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
                            orderStatusProcess(msgBody);
                        }
                        log.info("有任务，但其他兄弟抢去了，直接抢下一个任务.............");
                    }else {
                        log.info("有任务，但未到工作时间，休息1秒再看看.............");
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
