package com.ndc.channel.enumtype;

import java.util.HashMap;
import java.util.Map;

public class ProductRightUtil {

    private static Map<String, Map<String, String>> rightMap = new HashMap<>();

    static {

        Map<String, String> map = new HashMap<>();
        map.put("1", "更宽躺腿部空间");
        rightMap.put("seat_space_flag", map);

        Map<String, String> map1 = new HashMap<>();
        map.put("1", "行李优先收运");
        rightMap.put("bug_trans_flag", map1);


        Map<String, String> map2 = new HashMap<>();
        map.put("1", "专属值机柜台");
        rightMap.put("check_flag", map2);

        Map<String, String> map3 = new HashMap<>();
        map.put("1", "第一组登机");
        map.put("-1", "最后一组登机");
        map.put("2", "优先登机");
        rightMap.put("board_flag", map3);

        Map<String, String> map4 = new HashMap<>();
        map.put("0", "优选座位免费");
        map.put("-1", "不可提前选座");
        map.put("1", "优选座位需付费");
        map.put("2", "免费选择超级经济舱座位");
        map.put("3", "免费选择公务舱座位");
        rightMap.put("per_seat_flag", map4);

        Map<String, String> map5 = new HashMap<>();
        map.put("-1", "无免费托运行李");
        map.put("1", "有免费托运行李");
        rightMap.put("free_bag_flag", map5);

        Map<String, String> map6 = new HashMap<>();
        map.put("-1", "无免费托运行李");
        map.put("1", "有免费托运行李");
        rightMap.put("free_bag_flag", map6);

        Map<String, String> map7 = new HashMap<>();
        map.put("-1", "不得退票");
        map.put("0", "退票免收手续费");
        map.put("1", "收费退票");
        rightMap.put("ref_flag", map7);

        Map<String, String> map8 = new HashMap<>();
        map.put("-1", "不得改期");
        map.put("0", "改期免收手续费");
        map.put("1", "改期受限");
        rightMap.put("chg_flag", map8);

        Map<String, String> map9 = new HashMap<>();
        map.put("-1", "不允许升舱");
        map.put("0", "优先升舱");
        map.put("1", "允许升舱");
        rightMap.put("upg_flag", map9);
    }

    public static String getRightDesc(String  code, String val) {

        final Map<String, String> map = rightMap.get(code);
        if (map != null) {

            return map.get(val);
        }
        return null;
    }
}
