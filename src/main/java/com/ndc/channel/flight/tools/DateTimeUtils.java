package com.ndc.channel.flight.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DateTimeUtils {

    private static Logger logger = LoggerFactory.getLogger(DateTimeUtils.class);

	public static final String PATTEN_YYYYMMDD = "yyyyMMdd";

	public static final String PATTEN_YYYY_MM_DD = "yyyy-MM-dd";

	public static final String PATTEN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public static final String PATTEN_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

	public static final String PATTEN_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String PATTEN_YYYY_MM_DD_HHMM = "yyyy-MM-dd HHmm";

	public static final String PATTEN_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	
	public static final String PATTEN_YYYY_MM_DDHHMM = "yyyy-MM-ddHHmm";

	public static final String PATTEN_YYYY_MM = "yyyy-MM";

	public static final String PATTEN_YYYY_年_MM_月_DD_日_HH_MM = "yyyy年MM月dd日 HH:mm";

	public static final String PATTEN_YYYY_年_MM_月_DD_日 = "yyyy年MM月dd日";

	public static final String PATTEN_HH_MM = "HH:mm";

	public static final String PATTEN_HHMM = "HHmm";

	public static final String PATTEN_YYYY_MM_DDTHH_MM_SS_SSS_XXX = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

	public static final String PATTEN_YYYY_MM_DDTHH_MM_SS_XXX = "yyyy-MM-dd'T'HH:mm:ssXXX";

	public static final String PATTEN_YYYY_MM_DDTHH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";

	public static final String PATTEN_YYYY_MM_DD_HH_MM_SS_Z = "yyyy-MM-dd HH:mm:ss Z";
	
	public static final String PATTEN_DDMMMYY = "ddMMMyy";

	public static final String PATTEN_HUMMGL = "yyyy-MM-dd'T'HH:mm:ss";
	
	public static Date getCurrentDate() {
		return new Date();
	}

	public static Date parseStringToDate(String dateStr, String patten) {
		SimpleDateFormat format = new SimpleDateFormat(patten);
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			//logger.error("", e);
		}
		return null;
	}
	public static String parseDateToHHmm(String dateStr, String patten) {
		SimpleDateFormat format = new SimpleDateFormat(patten);
		try {
			Date date=format.parse(dateStr);
			return format.format(date);
		} catch (ParseException e) {
			logger.error("", e);
		}
		return null;
	}
	public static Date formatDate(Date dateStr, String patten) {
		return parseStringToDate(parseDateToString(dateStr, patten), patten);
	}

	public static String parseDateToString(Date dateStr, String patten) {
		if (dateStr == null) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat(patten);
		return format.format(dateStr);
	}

	public static String parseEngStringToString(String dateStr,
											 String sourcePatten, String patten) {
		return parseDateToEngString(parseEngStringToDate(dateStr, sourcePatten),
				patten);
	}

	public static String parseStringToString(String dateStr,
			String sourcePatten, String patten) {
		return parseDateToString(parseStringToDate(dateStr, sourcePatten),
				patten);
	}

	public static Date parseDateToDate(Date dateStr, String sourcePatten,
			String patten) {
		return parseStringToDate(parseDateToString(dateStr, sourcePatten),
				patten);
	}

	public static Date getOneMonthAgoDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1); // 得到当前系统时间的前一个月
		return calendar.getTime();
	}


	/**
	 * 当天的开始时间
	 * 
	 * @return
	 */
	public static Date startOfTodDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 当天的结束时间
	 * 
	 * @return
	 */
	public static Date endOfTodDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * 根据日期获取当天是周几
	 * @param datetime 日期
	 * @return 周几
	 */
	public static String dateToWeek(String datetime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
		Calendar cal = Calendar.getInstance();
		Date date;
		try {
			date = sdf.parse(datetime);
			cal.setTime(date);
		} catch (ParseException e) {
			logger.error("", e);
		}
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return weekDays[w];
	}

	/**
	 * 当前时间比航班时间大的情况 说明跨年了返回false
	 * 
	 * @param str1
	 *            航班时间
	 * @param str2
	 *            当前系统时间
	 * @return
	 */
	public static boolean compareDate(String str1, String str2) {
		SimpleDateFormat sdf = new SimpleDateFormat("MMMyyyy", Locale.ENGLISH);
		SimpleDateFormat sdf1 = new SimpleDateFormat("MMyyyy");
		try {
			Date date1 = sdf1.parse(str2);
			Date date2 = sdf.parse(str1);
			if (date1.getTime() > date2.getTime()) {
				return false;
			} else {
				return true;
			}
		} catch (ParseException e) {
			logger.error("", e);
		}
		return true;
	}

	public static Date parseEngStringToDate(String dateStr, String patten) {
		SimpleDateFormat format = new SimpleDateFormat(patten, Locale.ENGLISH);
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			//logger.error("", e);
		}
		return null;
	}

	/**
	 * 时间转英文年月日
	 *2017年8月7日
	 *@param date
	 *@param patten
	 *@return
	 *@author hejiang
	 */
	public static String parseDateToEngString(Date date, String patten) {
		SimpleDateFormat format = new SimpleDateFormat(patten, Locale.ENGLISH);
		return format.format(date);
	}
	/**
	 * 获取两个日期间的每一天日期
	 * @return
	 */
	public static List<LocalDateTime> getEveryDateStamp(Date startTime,Date endTime) {

		LocalDate start = dateToLocalDate(startTime);
		LocalDate end = dateToLocalDate(endTime);
		//获取两个日期间相差的天数
		int dayCount = (int)(endTime.getTime()/86400000-startTime.getTime()/86400000);
		List<LocalDateTime> dateArray = new ArrayList<>();
		DateTimeFormatter fommater = DateTimeFormatter.ofPattern(PATTEN_YYYY_MM_DD);
		ZoneId zoneId = ZoneId.systemDefault();
		for(int i=0;i<dayCount;i++){
			dateArray.add(start.atStartOfDay());
			start = start.plusDays(1);
			if(start.isAfter(end)){
				break;
			}
		}
		return dateArray;
	}
	
	/**
	 * 获取上月开始时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastMonthFirstDay(Date date) {
		Calendar calendar = Calendar.getInstance();// 获取当前日期
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取上月最后时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastMonthLastDay(Date date) {
		Calendar calendar = Calendar.getInstance();// 获取当前日期
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 获取当月开始时间
	 * 
	 * @return date
	 */
	public static Date getThisMonthFirstDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取当月最后时间
	 * 
	 * @return date
	 */
	public static Date getThisMonthLastDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 获取上周期开始时间(周一为每周第一天)；
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastWeekFirstDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);// 将每周第一天设为星期一，默认是星期天
		calendar.add(Calendar.DATE, -1 * 7);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取上周最后时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastWeekLastDay(Date date) {
		Calendar calendar = Calendar.getInstance();// 获取当前日期
		calendar.setTime(date);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);// 将每周第一天设为星期一，默认是星期天
		calendar.add(Calendar.DATE, -1 * 7);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 获取本周开始时间
	 * 
	 * @return date
	 */
	public static Date getThisWeekFirstDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 本周最后时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getThisWeekLastDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 获取加上指定日期后的新生成的每周周期；
	 * 
	 * @param date
	 * @param settlementDate
	 *            日期
	 * @return
	 */
	public static Date addSettlementDate(Date date, Integer settlementDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, settlementDate - 1);
		return calendar.getTime();
	}

	/**
	 * 获取星期周期
	 * 
	 * @param date
	 * @param settlementDate
	 * @return
	 */
	public static Map<String, Date> getSettlementWeekDate(Date date,
			Integer settlementDate) {
		Map<String, Date> map = new HashMap<String, Date>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		map.put("lastWeekFirstTime",
				addSettlementDate(getLastWeekFirstDay(date), settlementDate));
		map.put("lastWeekLastTime",
				addSettlementDate(getLastWeekLastDay(date), settlementDate));
		map.put("thisWeekFirstTime",
				addSettlementDate(getThisWeekFirstDay(date), settlementDate));
		map.put("thisWeekLastTime",
				addSettlementDate(getThisWeekLastDay(date), settlementDate));
		return map;
	}

	/**
	 * 获取月分周期
	 * 
	 * @param date
	 *            当前时间
	 * @param settlementDate
	 *            结算周期
	 * @return
	 */
	public static Map<String, Date> getSettlementMonthDate(Date date,
			Integer settlementDate) {
		Map<String, Date> map = new HashMap<String, Date>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		map.put("lastMonthFirstTime",
				addSettlementDate(getLastMonthFirstDay(date), settlementDate));
		map.put("lastMonthLastTime",
				addSettlementDate(getLastMonthLastDay(date), settlementDate));
		map.put("thisMonthFirstTime",
				addSettlementDate(getThisMonthFirstDay(date), settlementDate));
		map.put("thisMonthLastTime",
				addSettlementDate(getThisMonthLastDay(date), settlementDate));
		if (month == 1) {// 2月特殊处理
			int maxDay = 28;
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {// 闰年
				maxDay = 29;
			}
			if (settlementDate > maxDay) {
				StringBuilder sb = new StringBuilder().append(year).append("-")
						.append(month + 1).append("-").append(maxDay)
						.append(" 23:59:59");
				map.put("thisMonthLastTime", DateTimeUtils.parseStringToDate(
						sb.toString(), PATTEN_YYYY_MM_DD_HH_MM_SS));
				return map;
			}
		}
		if (month == 2) {// 2月特殊处理
			int maxDay = 28;
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {// 闰年
				maxDay = 29;
			}
			if (settlementDate > maxDay) {
				StringBuilder sb = new StringBuilder().append(year).append("-")
						.append(month - 1).append("-").append("31")
						.append(" 00:00:00");
				map.put("lastMonthFirstTime", DateTimeUtils.parseStringToDate(
						sb.toString(), PATTEN_YYYY_MM_DD_HH_MM_SS));
				sb = new StringBuilder().append(year).append("-").append(month)
						.append("-").append(maxDay).append(" 23:59:59");
				map.put("lastMonthLastTime", DateTimeUtils.parseStringToDate(
						sb.toString(), PATTEN_YYYY_MM_DD_HH_MM_SS));
				sb = new StringBuilder().append(year).append("-")
						.append(month + 1).append("-").append("01")
						.append(" 00:00:00");
				map.put("thisMonthFirstTime", DateTimeUtils.parseStringToDate(
						sb.toString(), PATTEN_YYYY_MM_DD_HH_MM_SS));
				calendar.add(Calendar.MONTH, 1);
				int nextMonthMaxDay = calendar
						.getActualMaximum(Calendar.DAY_OF_MONTH);
				sb = new StringBuilder().append(year).append("-")
						.append(month + 1).append("-").append(nextMonthMaxDay)
						.append(" 23:59:59");
				map.put("thisMonthLastTime", DateTimeUtils.parseStringToDate(
						sb.toString(), PATTEN_YYYY_MM_DD_HH_MM_SS));
				return map;
			}
		}
		if (month == 3) {// 2月特殊处理
			int maxDay = 28;
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {// 闰年
				maxDay = 29;
			}
			if (settlementDate > maxDay) {
				StringBuilder sb = new StringBuilder().append(year).append("-")
						.append(month - 1).append("-").append(maxDay)
						.append(" 00:00:00");
				map.put("lastMonthFirstTime", DateTimeUtils.parseStringToDate(
						sb.toString(), PATTEN_YYYY_MM_DD_HH_MM_SS));
				return map;
			}
		}
		return map;

	}

	/**
	 * 获取季度第一天
	 * @param year
	 * @param season
	 * @return
	 */
	public static Date getSeasonStart(String year, String season) {
		String month = "";
		if (season.equals("1")) {
			// 第一季度
			month = "01";
		} else if (season.equals("2")) {
			// 第二季度
			month = "04";
		} else if (season.equals("3")) {
			// 第三季度
			month = "07";
		} else if (season.equals("4")) {
			// 第四季度
			month = "10";
		}
		String time = year + "-" + month;
		return getThisMonthFirstDay(parseStringToDate(time, "yyyy-MM"));
	}

	/**
	 * 获取季度的第一天
	 * @return
	 */
	public static Date getCurrentSeasonStartTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int currentMonth = c.get(Calendar.MONTH) + 1;
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 3)
				c.set(Calendar.MONTH, 0);
			else if (currentMonth >= 4 && currentMonth <= 6)
				c.set(Calendar.MONTH, 3);
			else if (currentMonth >= 7 && currentMonth <= 9)
				c.set(Calendar.MONTH, 4);
			else if (currentMonth >= 10 && currentMonth <= 12)
				c.set(Calendar.MONTH, 9);
			c.set(Calendar.DATE, 1);
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			now = c.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	public static Date getLastSeasonStartTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MONTH, (c.get(Calendar.MONTH) / 3 - 1) * 3);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date getLastSeasonEndTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MONTH, (c.get(Calendar.MONTH) / 3 - 1) * 3 + 2);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR_OF_DAY, c.getActualMaximum(Calendar.HOUR_OF_DAY));
		c.set(Calendar.MINUTE, c.getActualMaximum(Calendar.MINUTE));
		c.set(Calendar.SECOND, c.getActualMaximum(Calendar.SECOND));
		c.set(Calendar.MILLISECOND, c.getActualMaximum(Calendar.MILLISECOND));
		return c.getTime();
	}

	/**
	 * 获取季度最后一天
	 * @param year
	 * @param season
	 * @return
	 */
	public static Date getSeasonEnd(String year, String season) {
		String month = "";
		if (season.equals("1")) {
			// 第一季度
			month = "03";
		} else if (season.equals("2")) {
			// 第二季度
			month = "06";
		} else if (season.equals("3")) {
			// 第三季度
			month = "09";
		} else if (season.equals("4")) {
			// 第四季度
			month = "12";
		}
		String time = year + "-" + month;
		return getThisMonthLastDay(parseStringToDate(time, "yyyy-MM"));
	}


	/**
	 * 年度第一天
	 * @param year
	 * @return
	 */
	public static Date getYearStart(String year) {
		return parseStringToDate(year+"-01-01 00:00:00", PATTEN_YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 年度最后一天
	 * @param year
	 * @return
	 */
	public static Date getYearEnd(String year) {
		return parseStringToDate(year+"-12-31 23:59:59", PATTEN_YYYY_MM_DD_HH_MM_SS);
	}
	
	/**
	 * 判断两个日期中间相差几天
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	public static Integer daysOfTwo(Date fDate, Date oDate) {
		Calendar cal1 = Calendar.getInstance();
        cal1.setTime(fDate);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(oDate);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2){ //不同一年
            int timeDistance = 0 ;
            for(int i = year1; i < year2; i ++){
                if(i%4==0 && i%100!=0 || i%400==0){//闰年
                    timeDistance += 366;
                }else{//不是闰年
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2-day1) ;
        }else{ //同年
            return day2-day1;
        }
	}
	
	/**
	 * 获取两个日期间的每一天日期
	 * @return
	 */
	public static String[] getEveryDate(Date startTime,Date endTime) {
		
		LocalDate start = dateToLocalDate(startTime);
		LocalDate end = dateToLocalDate(endTime);
		//获取两个日期间相差的天数
		int dayCount = (int)(endTime.getTime()/86400000-startTime.getTime()/86400000);
		String[] dateArray = new String[dayCount];
		DateTimeFormatter fommater = DateTimeFormatter.ofPattern(PATTEN_YYYY_MM_DD);
		for(int i=0;i<dateArray.length;i++){
			dateArray[i] = start.format(fommater);
			start = start.plusDays(1);
			if(start.isAfter(end)){
				break;
			}
		}
		return dateArray;
	}
	
	/**
	 * DATE转换为新的LocalDate
	 * @param date
	 * @return
	 */
	public static LocalDate dateToLocalDate(Date date) {
	    Instant instant = date.toInstant();
	    ZoneId zone = ZoneId.systemDefault();
	    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
	    return localDateTime.toLocalDate();
	}

	/**
	 * 获取两个月份间的每个月份
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String[] getEveryMonth(Date startTime, Date endTime) {
		LocalDate start = dateToLocalDate(startTime);
		LocalDate end = dateToLocalDate(endTime);
		//获取两个日期间相差的月份
		int monthCount = (end.getYear()-start.getYear())*12+(end.getMonth().getValue()-start.getMonth().getValue())+1;
		String[] monthArray = new String[monthCount];
		
		DateTimeFormatter fommater = DateTimeFormatter.ofPattern(PATTEN_YYYY_MM);
		for(int i=0;i<monthArray.length;i++){
			monthArray[i] = start.format(fommater);
			start = start.plusMonths(1);
			if(start.isAfter(end)){
				break;
			}
		}
		return monthArray;
	}
	
	/**
	 * 获取一个给定时间的年份
	 * @param date
	 * @return
	 */
	public static Integer getYear(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}
	
	/**
	 * 获取一个给定时间的月份
	 * @param date
	 * @return
	 */
	public static Integer getMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH);
	}
	
	/**
	 * 获取一个给定时间的该月第几天
	 * @param date
	 * @return
	 */
	public static Integer getDay(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	public static String[] getEverySeason(Date startTime, Date endTime) {
		LocalDate start = dateToLocalDate(startTime);
		LocalDate end = dateToLocalDate(endTime);
		//获取两个日期间相差的月份
		int monthCount = (end.getYear()-start.getYear())*12+(end.getMonth().getValue()-start.getMonth().getValue())+1;
		String[] seasonArray = new String[monthCount/3];
		
		int season = 1;
		for(int i=0;i<seasonArray.length;i++){
			String year = start.getYear()+"-";
			seasonArray[i] = year+season+"季度";
			start = start.plusMonths(3);
			season++;
			if(season==5){
				season=1;
			}
			if(start.isAfter(end)){
				break;
			}
		}
		return seasonArray;
	}

	public static String[] getEveryYear(Date startTime, Date endTime){
		Calendar st = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		st.setTime(startTime);
		end.setTime(endTime);
		int startY = st.get(Calendar.YEAR);
		int endY = end.get(Calendar.YEAR);
		int b = endY - startY;
		String[] years = new String[b+1];
		for (int i = 0; i < years.length; i++) {
			years[i] = String.valueOf(startY);
			startY++;
		}
		return years;
	}

	/**
	 * 比较两个时间大小
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Boolean checkDate(Date date1, Date date2){
		if(date1 == null || date2 == null){
			return false;
		}
		return date1.getTime() > date2.getTime() ? true : false;
	}
	
	/**
	 * 获取当前是周几 1,2,3,4,5,6,7这种
	 * @param date
	 * @return
	 */
	public static Integer getWeekDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		//一周第一天是否为星期天
		boolean isFirstSunday = (cal.getFirstDayOfWeek() == Calendar.SUNDAY);
		int weekDay = cal.get(Calendar.DAY_OF_WEEK);
		if(isFirstSunday){
			//一周第一天为星期天需要减一
			weekDay = weekDay - 1;
			if(weekDay == 0){
				weekDay = 7;
			}
		}
		return weekDay;
	}

	/**
	 * 获取当前是周几 0,1,2,3,4,5,6这种 星期天为0的
	 * @param date
	 * @return
	 */
	public static Integer getWeekDaySunDayIsZero(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		//一周第一天是否为星期天
		boolean isFirstSunday = (cal.getFirstDayOfWeek() == Calendar.SUNDAY);
		int weekDay = cal.get(Calendar.DAY_OF_WEEK);
		if(isFirstSunday){
			//一周第一天为星期天需要减一
			weekDay = weekDay - 1;
		}
		return weekDay;
	}

	
	/**
	 * 获取某时间前/后多少分钟的时间
	 * @param date
	 * @param minute 前传负值；后传正值
	 * @return
	 */
	public static Date getMinBeforeDate(Date date, Integer minute){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minute);
		return cal.getTime();
	}

	/**
	 * 获取月日
	 * @param trainDate
	 * @return
	 */
	public static String getMonthAndDay(Date trainDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(trainDate);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return month+"月"+day+"日";
	}

	public static String getTime(Date time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		if(minute<10){
			return hour+":0"+minute;
		}else{
			return hour+":"+minute;
		}
	}
	
	
	/**
	 * 判断两个日期中间相差多少分钟
	 * @param fDate 前时间
	 * @param oDate 后时间
	 * @return
	 */
	public static Long minsOfTwo(Date fDate, Date oDate) {
		Long time = oDate.getTime()-fDate.getTime();//获取时间差毫秒数
		return time/1000/60;
	}

	/**
	 * 将excel日期类型字符转换成需要的格式
	 * @param str
	 * @param patten
	 * @return
	 */
	public static String formatExcelDate(String str ,String patten) throws ParseException {
		Date excelDate = parseUnKnowStringToDate(str);
		return parseDateToString(excelDate, patten);
	}


	/**
	 * 将未指定格式的日期字符串转化成java.util.Date类型日期对象
	 * 注意此方法必须要有年月日，而且要年月日时分秒顺序排列,否则会出现错误数据
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date parseUnKnowStringToDate(String date) throws ParseException{
		String parse=date;
		parse=parse.replaceFirst("^[0-9]{4}([^0-9])", "yyyy$1");
		parse=parse.replaceFirst("^[0-9]{2}([^0-9])", "yy$1");
		parse=parse.replaceFirst("([^0-9])[0-9]{1,2}([^0-9])", "$1MM$2");
		parse=parse.replaceFirst("([^0-9])[0-9]{1,2}( ?)", "$1dd$2");
		parse=parse.replaceFirst("( )[0-9]{1,2}([^0-9])", "$1HH$2");
		parse=parse.replaceFirst("([^0-9])[0-9]{1,2}([^0-9]?)", "$1mm$2");
		parse=parse.replaceFirst("([^0-9])[0-9]{1,2}([^0-9]?)", "$1ss$2");
		DateFormat format=new SimpleDateFormat(parse);
		Date result=format.parse(date);
		return result;
	}

	/**
	 * 给一个日期加上指定天数
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDateSomeDay(Date date, int days){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
	}

	/**
	 * 给一个时间加上指定小时
	 * @param date
	 * @param hours
	 * @return
	 */
	public static Date addDateSomeHours(Date date, int hours){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, hours);
		return cal.getTime();
	}

	/**
	 * 过去时间
	 * @param date
	 * @param i
	 * @return
	 */
	public static Date reduceOneDay(Date date,int i) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, i);//当前时间减去一年，即一年前的时间
		return calendar.getTime();
	}

	/**
	 * 给一个时间加上指定分钟
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date addDateSomeMinute(Date date, int minute){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minute);
		return cal.getTime();
	}

	/**
	 * 截取日期到指定格式
	 * @author dingyj
	 * @Date 2019-05-10 16:25
	 */
	public static Date truncateDate(Date date, String patten) {
		return parseDateToDate(date, patten, patten);
	}

	/**
	 * 两个时间点之间相差多少小时
	 * @author dingyj
	 * @Date 2019-04-08 20:13
	 */
	public static int hourOfTwo(final Date beforeDate, final Date afterDate) {
		return (int) (minsOfTwo(beforeDate, afterDate) / 60);
	}

	/**
	 * 判断两个日期中间相差多少秒
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	public static Long secondsOfTwo(final Date fDate, final Date oDate) {
		Long time = oDate.getTime()-fDate.getTime();//获取时间差毫秒数
		return time/1000;
	}
	/**
	 * 转英文日期
	 * str -> str
	 * @author dingyj
	 * @Date 2019-07-11 18:54
	 */
	public static String parseStringToStringEnglish(String dateStr, String sourcePatten, String patten) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sourcePatten,Locale.ENGLISH);
		try {
			Date date = simpleDateFormat.parse(dateStr);
			SimpleDateFormat pattenFormat = new SimpleDateFormat(patten, Locale.ENGLISH);
			return pattenFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String parseEnDateStrToDateStr(String enDate){
		try {

			SimpleDateFormat format = new SimpleDateFormat(DateTimeUtils.PATTEN_DDMMMYY, Locale.ENGLISH);

			return new SimpleDateFormat(DateTimeUtils.PATTEN_YYYY_MM_DD).format(format.parse(enDate));
		}catch (Exception e){
			return null;
		}
	}

	public static void main(String[] args) {
		final String s = parseStringToString("2022-03-13T16:15:00.000+08:00", PATTEN_YYYY_MM_DDTHH_MM_SS_SSS_XXX, PATTEN_HH_MM);
		System.out.println(s);
	}
}
