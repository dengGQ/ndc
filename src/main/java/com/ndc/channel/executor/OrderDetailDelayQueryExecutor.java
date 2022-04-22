package com.ndc.channel.executor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

/**
 * NDC订单明细延迟查询
 */
@Slf4j
@Component
public class OrderDetailDelayQueryExecutor {

    public static final String orderStatusQueryKey1 = "order_status_query_key";
    public static final String orderStatusQueryKey = "orderStatusQueryKey1";

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private Map<String, NdcOrderDetailHandler> orderDetailHandlerMap;

    private static Thread th = null;

    private final DelayTask delayTask = new DelayTask();

    @PostConstruct
    public void initWorker() {
        th = new Thread(delayTask);
        th.setDaemon(true);
        th.start();
    }

    public void submitTask(String msgBody, long delaySecond) {

        redisUtils.zsetAddWithScore(orderStatusQueryKey, msgBody, System.currentTimeMillis() + delaySecond*1000);

        if (th == null || th.getState() == Thread.State.TERMINATED) {
            // 有新任务提交，判断任务线程是否还在，不在的话重启一个工作线程
            initWorker();
        }else if(th.getState() == Thread.State.TIMED_WAITING) {
            // 有新任务提交，提前唤醒休眠的线程
            th.interrupt();
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
                Set<ZSetOperations.TypedTuple> tupleSet = redisUtils.rangeWithScores(orderStatusQueryKey, 0, 4);

                if (tupleSet == null || tupleSet.size() ==0) {
                    log.info("没任务休息会.............");
                    break;
                }

                doJob(tupleSet);
            }
        }

        private void doJob(Set<ZSetOperations.TypedTuple> tupleSet) {
            try {

                long currentScore = System.currentTimeMillis();

                // 本次任务中未到期的最小等待时间
                long minWaitTime = 0;

                // 到期任务处理
                for (ZSetOperations.TypedTuple typedTuple : tupleSet) {

                    long timeDiff = typedTuple.getScore().longValue() - currentScore;

                    String msgBody = typedTuple.getValue().toString();

                    if ( timeDiff <= 0 ) {

                        if (redisUtils.zsetRem(orderStatusQueryKey, msgBody) > 0) {
                            log.info("开始干活............msgBody={}", msgBody);
                            orderStatusProcess(msgBody);
                        }else {
                            log.info("有任务到期，但是被其他兄弟抢去了..................");
                        }
                    }else if (minWaitTime == 0) { // tupleSet本身就是按分值递增的有序集合

                        minWaitTime = timeDiff;
                    }
                }

                // 本次提取任务中有未到期的
                if (minWaitTime > 0) {

                    log.info("有任务未到期，休息{}毫秒再看看.............", minWaitTime);
                    Thread.sleep(minWaitTime);
                }
            } catch (InterruptedException e) {
                log.error("线程中断异常", e);
            } catch (Exception e) {
                log.error("订单状态刷新异常", e);
            }
        }
    }
}
