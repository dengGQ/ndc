package com.ndc.channel.flight;

import com.alibaba.fastjson.JSON;
import com.ndc.channel.ChannelApplication;
import com.ndc.channel.executor.OrderDetailDelayQueryExecutor;
import com.ndc.channel.flight.dto.MsgBody;
import com.ndc.channel.flight.dto.createOrder.CorpApiOrderFlightTicketParams;
import com.ndc.channel.flight.dto.createOrder.CorpApiOrderPassengerParams;
import com.ndc.channel.flight.dto.createOrder.FlightOrderCreateReq;
import com.ndc.channel.flight.dto.flightSearch.CorpApiFlightListDataV2;
import com.ndc.channel.flight.dto.orderDetail.NdcOrderDetailData;
import com.ndc.channel.flight.dto.orderPay.OrderPayReqParams;
import com.ndc.channel.flight.dto.refund.RefundChangeMoneyQueryParams;
import com.ndc.channel.flight.dto.refund.RefundChangeMoneyQueryResp;
import com.ndc.channel.flight.dto.verifyPrice.CorpApiFlightVerifyPriceData;
import com.ndc.channel.flight.dto.verifyPrice.FeiBaApiVerifyPriceReq;
import com.ndc.channel.flight.dto.verifyPrice.FeibaApiVerificationParams;
import com.ndc.channel.flight.handler.*;
import com.ndc.channel.mapper.NdcFlightApiOrderRelMapper;
import com.ndc.channel.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChannelApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@Slf4j
public class MuNdcFlightSearchServiceImplTests {

    @Resource
    private NdcFlightSearchHandler searchHandler;

    @Resource
    private NdcFlightVerifyPriceHandler verifyPriceHandler;

    @Resource
    private NdcFlightCreateOrderHandler createOrderHandler;

    @Resource
    private NdcFlightOrderPayHandler orderPayHandler;

    @Resource
    private NdcFlightOrderDetailHandler orderDetailHandler;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private OrderDetailDelayQueryExecutor queryExecutor;

    @Resource
    private NdcFlightOrderRefundHandler orderRefundHandler;

    @Resource
    private NdcFlightRefundOrderDetailHandler detailHandler;

    @Test
    public void ndcFlightSearch() {
        final List<CorpApiFlightListDataV2> corpApiFlightListDataV2s = searchHandler.flightSearch("2022-06-26", "SHA", "PEK");
        log.info("ndcFlightSearch={}", JSON.toJSONString(corpApiFlightListDataV2s));
    }

    @Test
    public void offerPrice() {

        FeiBaApiVerifyPriceReq req = new FeiBaApiVerifyPriceReq();
        final FeibaApiVerificationParams verificationParams = new FeibaApiVerificationParams();

        verificationParams.setFlightId("2022-06-2609301200MU5153SHAPEK");
        verificationParams.setTicketId("2022-06-2609301200MU5153SHAPEKY1@10793");

        req.setPriceVerificationParams(verificationParams);
        final CorpApiFlightVerifyPriceData corpApiFlightVerifyPriceData = verifyPriceHandler.verifyPrice(req);

        log.info("offerPrice={}", JSON.toJSONString(corpApiFlightVerifyPriceData));
    }


    @Test
    public void createOrder() {

        FlightOrderCreateReq flightOrderCreateReq = new FlightOrderCreateReq();

        CorpApiOrderFlightTicketParams ticketParams = new CorpApiOrderFlightTicketParams();
        ticketParams.setFlightId("2022-06-2609301200MU5153SHAPEK");
        ticketParams.setTicketId("2022-06-2609301200MU5153SHAPEKY1@10793");
        ticketParams.setSeatClassCode("Y");
        ticketParams.setPrice(new BigDecimal("1630"));

        flightOrderCreateReq.setTickets(Arrays.asList(ticketParams));

        CorpApiOrderPassengerParams passengerParams = new CorpApiOrderPassengerParams();
        passengerParams.setFlightPassengerName("测试");
        passengerParams.setPhone("18611312771");
        passengerParams.setBirthday("1994-08-20");
        passengerParams.setIdcardCode("411302199408201314");
        passengerParams.setIdcardType("1");
        passengerParams.setSex("1");
        flightOrderCreateReq.setPassengers(Arrays.asList(passengerParams));


//        OrderContactParams contactParams = new OrderContactParams();
//        contactParams.setName("邓国泉");
//        contactParams.setPhone("18611312771");
//        List<OrderContactParams> objects = new ArrayList<>();
//        objects.add(contactParams);
//        flightOrderCreateReq.setContacts(objects);

        createOrderHandler.createOrder(flightOrderCreateReq);
    }


    @Test
    public void orderPay() {

        final OrderPayReqParams payReqParams = new OrderPayReqParams();

        payReqParams.setOrderNumber("1022030800206114");
        orderPayHandler.orderPay(payReqParams);
    }


    @Test
    public void orderDetail() {

        // 1022030800206118 已退
        // 1022030900206462
        // 1022031000207068
        final NdcOrderDetailData ndcOrderDetailData = orderDetailHandler.orderDetail("1022031400207425");

        System.out.println(JSON.toJSONString(ndcOrderDetailData));
    }

    @Test
    public void refundMoneyQuery() {

        RefundChangeMoneyQueryParams params = new RefundChangeMoneyQueryParams();

        params.setRefundWay(Byte.valueOf("1"));
        params.setChannelOrderNumber("1022030800206118");
        params.setTicketNumList(Arrays.asList("7811157639813"));

        RefundChangeMoneyQueryResp queryResp = orderRefundHandler.refundMoneyQuery(params);
    }


    @Resource
    private NdcFlightApiOrderRelMapper orderRelMapper;

    @Test
    public void refundOrderQuery() {
        final NdcOrderDetailData response = detailHandler.orderDetail("1022030800206118");

        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void afterCreateOrder () {

        final MsgBody msgBody = new MsgBody();
        msgBody.setMsgType("1");
        msgBody.setBusinessNumber("1022031100207207");
        final String s = JSON.toJSONString(msgBody);

        queryExecutor.submitTask(s, 5);
        while (true){}
    }

    @Test
    public void refundNotice() {
        String str = "{\"channelOrderNumber\":\"1022032500211472\",\"orderStatus\":\"RF\",\"refundMoney\":1870.0,\"ticketInfoList\":[{\"idCardNo\":\"42080219910522033X\",\"passengerName\":\"测试\",\"refundAmount\":1870.0,\"refundAuditingStatus\":\"501\",\"refundFee\":0.0,\"ticketNumber\":\"7811157642985\",\"ticketStatus\":\"RF\"}]}";

        final NdcOrderDetailData detailData = JSON.parseObject(str, NdcOrderDetailData.class);
        detailHandler.statusChangeNotice(detailData);
    }

    static class BitMap{

        // 能存下整数的个数：比如15个整数，len=15
        private int len;
        private byte[] byteArray;

        public BitMap(int len) {
            this.len = len;
            byteArray = new byte[(len>>3)+1];
        }

        public void set(int num, Boolean bool) {

            if (bool) {

                byteArray[num/8] |= 1<< (num%8);
            }else {
                byteArray[num/8] &= ~(1<< (num%8));
            }
        }

        /**
         * 将num所在的位置从0变成1
         * 将1左移position位后，position位置上自然就是1，其他位置都是0
         * 然后和以前的数据做‘或’运算，这样，以前的数据position位置上就替换成1了，其他位置保持不变
         */
        public void add(int num) {
            byteArray[getIdx(num)] |= 1 << getPosition(num);
        }

        /**
         * 将num所在的位置从0变成1
         * 将1左移position位后，position位置上自然就是1，其他位置都是0。然后再取反就变成position位置是0，其他位置都是1
         * 然后和以前的数据做‘与’运算，这样，以前的数据position位置上替换成0了，其他位置保持不变
         * @param num
         */
        public void remove(int num) {
            byteArray[getIdx(num)] &= ~(1 << getPosition(num));
        }

        /**
         * 判断num是否存在
         * 同样将1左移position位使position位置上变成1，其他位置都是0，然后用这个数跟原数做与运算
         * 因为其他位置都是0，只有position位置是1，所以只有原数据position位置也是1运算后的结果才会是1，否则就是0
         * 如此边确定了num是否存在了
         * @param num
         * @return
         */
        public boolean isExits(int num) {

            return (byteArray[num/8] & (1 << (num % 8))) != 0 ? true : false;
        }

        // num在byte[]数组中的位置
        public int getIdx(int num) {
            return num >> 3;
        }

        // num在byte[idx]中第几位, 等价于：num%8
        public int getPosition(int num) {
            return num & 0x07;
        }

        public byte[] getBitMap(){
            return byteArray;
        }

        public int getLen() {
            return len;
        }
    }

    static class ListNode{
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 输入两个链表, 为位数逆序存储的数字，要求逆序输出这两个数字的和
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode head=null, tail=null;
        int carry = 0;

        while (l1 != null || l2 != null) {

            int n1 = l1 != null ?l1.val : 0;

            int n2 = l2 != null ?l2.val : 0;

            int sum = n1 + n2 + carry;

            if (head == null) {
                head = tail = new ListNode(sum % 10);
            }else {
                tail = tail.next = new ListNode(sum % 10);
            }

            carry = sum / 10;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (carry > 0) {
            tail.next = new ListNode(carry);
        }

        return head;
    }


    /**
     * 计算给定字符串中不包含重复字符的子串的最长个数
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> characters = new HashSet<>();
        int len= s.length();

        int rp = -1; // 右指针，标识还没开始滑动
        int n = 0; // 记录最长子串的长度
        // i 标识起始指针（左指针）
        for (int i =0; i < len; ++i) {

            // 右指针动取字符，将没有重复的字符记录到set同时结束滑动
            char _s;
            while ((rp+1) < len && !characters.contains(_s = s.charAt(rp + 1))) {
                characters.add(_s); //存储从i开始到rp截止不重复的字符
                ++rp;
            }
            n = Math.max(n, rp-i+1);

            // 左边指针向右滑动,删除一个字符
            characters.remove(s.charAt(i));
        }

        return n;
    }

    /**
     * 寻找正序数组中位数:
     * 1. 需找到k(i,j), i,j分别代表划分两个数组的位置
     * 2. 保证k位置前的数组长度与k位置后的数组长度一致且k位置前的最大值小于k位置后的最小值
     * ps: k = i + j, 所有要满足条件2需要
     * > i+j = m-i+n-j+1 -> j = (m+n+1)/2 - i
     * > max(Left) <= min(Right)
     *
     *
     * nums1 = {4, 8, 10}, nums2 = {2, 7, 9}
     * m = nums1.len, n = nums2.len
     *
     * left = 0, right = m;
     *
     *  ==>  i=(left+right)/2 = 1, j=(m+n+1)/2 -i = 2
     *       left: 4, 2, 7
     *       right: 8, 10, 9
     *       min(right) = 8 > max(left) = 7 -> left=i+1=2
     *
     * left = 2, right = 3
     * ==> i=(left+right)/2 = 2 j=(m+n+1)/2 - i = 1
     *      left:  4, 8, 2
     *      right: 10, 7, 9
     *      min(right) = 7 < max(left) = 8 -> right=i-1=1
     *
     * left = 2, right = 1;
     * end
     *
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // 确保nums1.len < nums2.len
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length, n=nums2.length;

        int left = 0, right = m;
        int maxL = 0, minR = 0; // 前部分最大值和后部分最大值

        while (left <= right) {
            int i = (left + right) / 2;
            int j = (m + n +1)/2 -i;

            int l_nums1_max = i == 0 ? Integer.MIN_VALUE: nums1[i-1];
            int l_nums2_max = j == 0 ? Integer.MIN_VALUE : nums2[j-1];

            int r_nums1_min = i == m ? Integer.MAX_VALUE : nums1[i];
            int r_nums2_min = j == n ? Integer.MAX_VALUE : nums2[j];

            if (l_nums1_max <= r_nums2_min) {
                maxL = Math.max(l_nums2_max, l_nums1_max);
                minR = Math.min(r_nums2_min, r_nums1_min);

                left = i + 1;
            }else {

                right = i - 1;
            }
        }

        return (m+n) % 2 == 0 ? (maxL + minR) /2.0 : maxL;
    }

    /**
     * 动态规划
     * 最长回文子串
     *
     * 1, 2, 3, 4, 6, 7, 8,
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {

        /**
         * s.len > 2
         * subMaxLen=1
         * P(i,j) 表示字符串S的第i到j个字母组成的串是否为回文串:
         * true: 表示 Si...Sj是回文串
         * false: 其他情况（不是回文串或i>j）
         *
         * P(i,j) = P(i+1, j-1) & (Si == Sj)
         * 方程的含义：
         *  Si...Sj是回文的必要条件
         *  1. Si+1...Sj-1是回文
         *  2. Si == Sj
         * 也就是S[i+1:j-1]是回文串且S的第i和j个字母相同时，S[i,j]才是回文串P(i,j)才为true
         *
         * 最终答案：P(i,j)=true中 j - i + 1的最大值.
         *
         */

        final int len = s.length();
        if (len < 2) {
            return s;
        }

        final char[] charArray = s.toCharArray();

        boolean[][] dp = new boolean[len][len];
        // 初始化所有单字符都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        //先遍历子串 L子串的长度
        int maxL = 1;
        int be = 0;
        for(int L = 2; L <= len; L++) {
            for (int i = 0; i < len; i++) {

                int j = L + i - 1;

                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                }else {

                    if (j - i < 3) {

                        dp[i][j] = true;
                    }else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                }

                if (dp[i][j] && j-i+1 > maxL) {
                    maxL = j-i+1;
                    be = i;
                }
            }
        }
        return s.substring(be, be+maxL);
    }

    /**
     * 最小回文扩展
     * 最小回文子串长度1或2
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {

        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {

            int len = expandAroundCenter(s, i, i); // 从回文长度为1开始
            int len1 = expandAroundCenter(s, i, i+1); // 从回文长度为2开始

            final int maxL = Math.max(len1, len);
            if (maxL > end - start) {
                start = i - (len - 1)/2;
                end = i + len/2;
            }
        }

        return s.substring(start, end+1);
    }
    public static int expandAroundCenter(String s, int left, int right) {

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }

        return right - left - 1;
    }

    public static void main(String[] args) {

        final String s = longestPalindrome("cababa");
        final String s1 = longestPalindrome2("cababa");
        System.out.println(s);
        System.out.println(s1);

        /*ChannelOKHttpService channelOKHttpService = new ChannelOKHttpService();

        final String s = channelOKHttpService.doGet("https://www.cospic.cc/");

        try {

            final Document document = Jsoup.parse(s);

            final Elements elements = document.getElementsByClass("item-thumbnail");

            for (Element element : elements) {
                final Elements img = element.select("a img");
                final String src = img.attr("data-src");
                System.out.println(src);
            }

        }catch (Exception e){}*/


    }

}
