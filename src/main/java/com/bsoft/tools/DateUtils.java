package com.bsoft.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.h2.util.New;

/**
 * 日期、时间转换处理工具
 * 
 * <pre>
 * 本类提供时间日期、处理转化处理的静态方法，主要方法有：
 * 1、转化时间成指定格式的字符串 convertDateTime()
 * 2、将指定格式的字符串日期时间转化为Date类型的日期时间 parseDateTime()
 * 3、日期 加 年，月，天，时，分，秒 数 addDateTime()
 * 
 * 4、获取当前时间，格式：yyyy-MM-dd getCurrentDate_YYYYMMDD()
 * 5、获取当前时间，格式：yyyy-MM-dd HH:mm getCurrentDate_YYYYMMDDHHMM()
 * 6、获取当前时间，格式：yyyy-MM-dd HH:mm:ss getCurrentDate_YYYYMMDDHHMMSS()
 * 7、获取当前时间，格式：yyyyMMddHHmmss getCurrentDate_YYYYMMDDHHMMSS_EX () 
 * 8、获取当前时间，格式：yyyy年MM月dd日 getCurrentDate_YYYYMMDD_CN()
 * 9、获取当前时间，格式：yyyy年MM月dd日HH时mm分 getCurrentDate_YYYYMMDDHHMM_CN()
 * 10、获取当前时间，格式：yyyy年MM月dd日HH时mm分ss秒 getCurrentDate_YYYYMMDDHHMMSS_CN()
 * 
 * 11、转换时间，格式：yyyy-MM-dd convertDateTime_YYYYMMDD()
 * 12、转换时间，格式：yyyy-MM-dd HH:mm convertDateTime_YYYYMMDDHHMM()
 * 13、转换时间，格式：yyyy-MM-dd HH:mm:ss convertDateTime_YYYYMMDDHHMMSS()
 * 14、转换时间，格式：dd日HH:mm convertDateTime_DDHHMM()
 * 15、转换时间，格式：HH:mm:ss convertDateTime_HHMMSS()
 * 16、转换时间，格式：HH:mm convertDateTime_HHMM()
 * 17、转换时间，格式：yyyy年MM月dd日 convertDateTime_YYYYMMDD_CN()
 * 18、转换时间，格式：yyyy年MM月dd日HH时mm分 convertDateTime_YYYYMMDDHHMM_CN()
 * 19、转换时间，格式：yyyy年MM月dd日HH时mm分ss秒 convertDateTime_YYYYMMDDHHMMSS_CN()
 * 
 * 20、解析时间，格式：yyyy-MM-dd parseDateTime_YYYYMMDD()
 * 21、解析时间，格式：yyyy-MM-dd HH:mm parseDateTime_YYYYMMDDHHMM()
 * 22、解析时间，格式：yyyy-MM-dd HH:mm:ss parseDateTime_YYYYMMDDHHMMSS()
 * 23、解析时间，格式：yyyy年MM月dd parseDateTime_YYYYMMDD_CN()
 * 24、解析时间，格式：yyyy年MM月dd日HH时mm分 parseDateTime_YYYYMMDDHHMM_CN()
 * 25、解析时间，格式：yyyy年MM月dd日HH时mm分ss秒 parseDateTime_YYYYMMDDHHMMSS_CN()
 * 
 * 26、日期 加 年 数 addYears()
 * 27、日期 加 月 数 addMonths()
 * 28、日期 加 天 数 addDays()
 * 29、日期 加 小时 数 addHours()
 * 30、日期 加 分 数 addMinutes()
 * 31、日期 加 秒 数 addSeconds()
 * 32、生成 MS SQL SERVER 的日期字段的日期值 nowForMSSQLDateField()
 * 33、取星期几 getWeekByDate()
 * 34、获取两个 Date 相差的 天数 getDays()
 * 35、获取两个 Date 相差的 分钟数 getMinutes()
 * 36、获取两个 Date 相差的 分钟数 getMinutes_No_abs()
 * 37、生成日期列表 listDays()
 * </pre>
 * 
 * @author dh
 */
public class DateUtils {

	/**
	 * 转化时间成指定格式的字符串
	 * 
	 * @param currentDate
	 *            待转化给定日期时间
	 * @param datetimeFormat
	 *            为日期、时间指定的格式
	 * @return 按指定格式转后的日期、时间字符串
	 * 
	 */
	public static String convertDateTime(Date currentDate, String datetimeFormat) {
		if (currentDate == null || "".equals(currentDate)) {
			return "";
		} else if (datetimeFormat == null || "".equals(datetimeFormat)) {
			return "";
		} else {
			try {
				SimpleDateFormat formatter = new SimpleDateFormat(datetimeFormat);
				return formatter.format(currentDate);
			} catch (Exception e) {
				return "";
			}
		}
	}

	/**
	 * 将指定格式的字符串日期时间转化为Date类型的日期时间
	 * 
	 * @param datetimeStr
	 *            指定格式的日期日期字符串
	 * @param datetimeFormat
	 *            为日期、时间指定的格式
	 * @return 转换成的Date类型的日期时间
	 */
	public static synchronized Date parseDateTime(String datetimeStr, String datetimeFormat) {
		Date parsedDate = null;
		if (datetimeStr == null || "".equals(datetimeStr)) {
			return parsedDate;
		}
		if (datetimeFormat == null || "".equals(datetimeFormat)) {
			return parsedDate;
		}
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(datetimeFormat);
			parsedDate = (Date) formatter.parse(datetimeStr);
		} catch (Exception e) {
		}
		return parsedDate;
	}

	/**
	 * 日期 加 年，月，天，时，分，秒 数
	 * 
	 * @param currentDate
	 *            当前时间
	 * @param AddType
	 *            添加类型 1 年，2月， 天， 时， 分，秒
	 * @param dateTimeCount
	 *            数量
	 * @return 加后的日期
	 */
	public static synchronized Date addDateTime(Date currentDate, int AddType, int dateTimeCount) {
		Date addedDate = currentDate;
		if (currentDate != null && dateTimeCount != 0) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);
			calendar.add(AddType, dateTimeCount);
			addedDate = calendar.getTime();
		}
		return addedDate;
	}

	// ///////////////////////////////////////////////////////////////////////////////////
	/**
	 * 获取当前时间 格式：yyyy-MM-dd
	 * 
	 * @return 当前时间的字符串形式
	 */
	public static String getCurrentDate_YYYYMMDD() {
		return convertDateTime(new Date(), "yyyy-MM-dd");
	}

	/**
	 * 获取当前时间 格式：yyyy-MM-dd HH:mm
	 * 
	 * @return 当前时间的字符串形式
	 */
	public static String getCurrentDate_YYYYMMDDHHMM() {
		return convertDateTime(new Date(), "yyyy-MM-dd HH:mm");
	}

	/**
	 * 获取当前时间 格式：yyyy-MM-dd HH
	 * 
	 * @return 当前时间的字符串形式
	 */
	public static String getCurrentDate_YYYYMMDDHH() {
		return convertDateTime(new Date(), "yyyy-MM-dd HH");
	}

	/**
	 * 获取当前时间 格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return 当前时间的字符串形式
	 */
	public static String getCurrentDate_YYYYMMDDHHMMSS() {
		return convertDateTime(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取当前时间 格式：yyyyMMddHHmmss
	 * 
	 * @return 当前时间的字符串形式
	 */
	public static String getCurrentDate_YYYYMMDDHHMMSS_EX() {
		return convertDateTime(new Date(), "yyyyMMddHHmmss");
	}

	// ///////////////////////////////////////////////////////////////////////////////////

	// ///////////////////////////////////////////////////////////////////////////////////
	/**
	 * 获取当前时间 格式：yyyy年MM月dd日
	 * 
	 * @return 当前时间的字符串形式
	 */
	public static String getCurrentDate_YYYYMMDD_CN() {
		return convertDateTime(new Date(), "yyyy年MM月dd日");
	}

	/**
	 * 获取当前时间 格式：yyyy年MM月dd日HH时mm分
	 * 
	 * @return 当前时间的字符串形式
	 */
	public static String getCurrentDate_YYYYMMDDHHMM_CN() {
		return convertDateTime(new Date(), "yyyy年MM月dd日HH时mm分");
	}

	/**
	 * 获取当前时间 格式：yyyy年MM月dd日HH时mm分ss秒
	 * 
	 * @return 当前时间的字符串形式
	 */
	public static String getCurrentDate_YYYYMMDDHHMMSS_CN() {
		return convertDateTime(new Date(), "yyyy年MM月dd日HH时mm分ss秒");
	}

	// ///////////////////////////////////////////////////////////////////////////////////

	// ///////////////////////////////////////////////////////////////////////////////////
	/**
	 * 转换时间 格式：yyyy-MM-dd
	 * 
	 * @param currentDate
	 *            等转换日期
	 * @return 当前时间的字符串形式
	 */
	public static String convertDateTime_YYYYMMDD(Date currentDate) {
		return convertDateTime(currentDate, "yyyy-MM-dd");
	}

	/**
	 * 转换时间 格式：yyyy-MM-dd HH:mm
	 * 
	 * @param currentDate
	 *            等转换日期
	 * @return 当前时间的字符串形式
	 */
	public static String convertDateTime_YYYYMMDDHHMM(Date currentDate) {
		return convertDateTime(currentDate, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 转换时间 格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param currentDate
	 *            等转换日期
	 * @return 当前时间的字符串形式
	 */
	public static String convertDateTime_YYYYMMDDHHMMSS(Date currentDate) {
		return convertDateTime(currentDate, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 转换时间 格式：dd日HH:mm
	 * 
	 * @param currentDate
	 *            等转换日期
	 * @return 当前时间的字符串形式
	 */
	public static String convertDateTime_DDHHMM(Date currentDate) {
		return convertDateTime(currentDate, "dd日HH:mm");
	}

	/**
	 * 转换时间 格式：HH:mm:ss
	 * 
	 * @param currentDate
	 *            等转换日期
	 * @return 当前时间的字符串形式
	 */
	public static String convertDateTime_HHMMSS(Date currentDate) {
		return convertDateTime(currentDate, "HH:mm:ss");
	}

	/**
	 * 转换时间 格式：HH:mm
	 * 
	 * @param currentDate
	 *            等转换日期
	 * @return 当前时间的字符串形式
	 */
	public static String convertDateTime_HHMM(Date currentDate) {
		return convertDateTime(currentDate, "HH:mm");
	}

	// ///////////////////////////////////////////////////////////////////////////////////

	// ///////////////////////////////////////////////////////////////////////////////////
	/**
	 * 转换时间 格式：yyyy年MM月dd日
	 * 
	 * @param currentDate
	 *            等转换日期
	 * @return 当前时间的字符串形式
	 */
	public static String convertDateTime_YYYYMMDD_CN(Date currentDate) {
		return convertDateTime(currentDate, "yyyy年MM月dd日");
	}

	/**
	 * 转换时间 格式：yyyy年MM月dd日HH时mm分
	 * 
	 * @param currentDate
	 *            等转换日期
	 * @return 当前时间的字符串形式
	 */
	public static String convertDateTime_YYYYMMDDHHMM_CN(Date currentDate) {
		return convertDateTime(currentDate, "yyyy年MM月dd日HH时mm分");
	}

	/**
	 * 转换时间 格式：yyyy年MM月dd日HH时mm分ss秒
	 * 
	 * @param currentDate
	 *            等转换日期
	 * @return 当前时间的字符串形式
	 */
	public static String convertDateTime_YYYYMMDDHHMMSS_CN(Date currentDate) {
		return convertDateTime(currentDate, "yyyy年MM月dd日HH时mm分ss秒");
	}

	// ///////////////////////////////////////////////////////////////////////////////////

	// ///////////////////////////////////////////////////////////////////////////////////
	/**
	 * 解析时间 格式：yyyy-MM-dd
	 * 
	 * @param currentDate
	 *            等解析日期
	 * @return 当前时间的字符串形式
	 */
	public static Date parseDateTime_YYYYMMDD(String currentDate) {
		return parseDateTime(currentDate, "yyyy-MM-dd");
	}

	/**
	 * 解析时间 格式：yyyy-MM-dd HH:mm
	 * 
	 * @param currentDate
	 *            等解析日期
	 * @return 当前时间的字符串形式
	 */
	public static Date parseDateTime_YYYYMMDDHHMM(String currentDate) {
		return parseDateTime(currentDate, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 解析时间 格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param currentDate
	 *            等解析日期
	 * @return 当前时间的字符串形式
	 */
	public static Date parseDateTime_YYYYMMDDHHMMSS(String currentDate) {
		return parseDateTime(currentDate, "yyyy-MM-dd HH:mm:ss");
	}

	// ///////////////////////////////////////////////////////////////////////////////////

	// ///////////////////////////////////////////////////////////////////////////////////
	/**
	 * 解析时间 格式：yyyy年MM月dd
	 * 
	 * @param currentDate
	 *            等解析日期
	 * @return 当前时间的字符串形式
	 */
	public static Date parseDateTime_YYYYMMDD_CN(String currentDate) {
		return parseDateTime(currentDate, "yyyy年MM月dd");
	}

	/**
	 * 解析时间 格式：yyyy年MM月dd日HH时mm分
	 * 
	 * @param currentDate
	 *            等解析日期
	 * @return 当前时间的字符串形式
	 */
	public static Date parseDateTime_YYYYMMDDHHMM_CN(String currentDate) {
		return parseDateTime(currentDate, "yyyy年MM月dd日HH时mm分");
	}

	/**
	 * 解析时间 格式：yyyy年MM月dd日HH时mm分ss秒
	 * 
	 * @param currentDate
	 *            等解析日期
	 * @return 当前时间的字符串形式
	 */
	public static Date parseDateTime_YYYYMMDDHHMMSS_CN(String currentDate) {
		return parseDateTime(currentDate, "yyyy年MM月dd日HH时mm分ss秒");
	}

	// ///////////////////////////////////////////////////////////////////////////////////

	// ///////////////////////////////////////////////////////////////////////////////////
	/**
	 * 日期 加 年 数
	 * 
	 * @param currentDate
	 *            当前时间
	 * @param years
	 *            年数量
	 * @return 加后的日期
	 */
	public static Date addYears(Date currentDate, int years) {
		return addDateTime(currentDate, Calendar.YEAR, years);
	}

	/**
	 * 日期 加 月 数
	 * 
	 * @param currentDate
	 *            当前时间
	 * @param months
	 *            月数量
	 * @return 加后的日期
	 */
	public static Date addMonths(Date currentDate, int months) {
		return addDateTime(currentDate, Calendar.MONTH, months);
	}

	/**
	 * 日期 加 天 数
	 * 
	 * @param currentDate
	 *            当前时间
	 * @param days
	 *            日数量
	 * @return 加后的日期
	 */
	public static Date addDays(Date currentDate, int days) {
		return addDateTime(currentDate, Calendar.DATE, days);
	}

	/**
	 * 日期 加 小时 数
	 * 
	 * @param currentDate
	 *            当前时间
	 * @param hours
	 *            小时数量
	 * @return 加后的日期
	 */
	public static Date addHours(Date currentDate, int hours) {
		return addDateTime(currentDate, Calendar.HOUR, hours);
	}

	/**
	 * 日期 加 分 数
	 * 
	 * @param currentDate
	 *            当前时间
	 * @param minutes
	 *            分钟数量
	 * @return 加后的日期
	 */
	public static Date addMinutes(Date currentDate, int minutes) {
		return addDateTime(currentDate, Calendar.MINUTE, minutes);
	}

	/**
	 * 日期 加 秒 数
	 * 
	 * @param currentDate
	 *            当前时间
	 * @param seconds
	 *            秒数量
	 * @return 加后的日期
	 */
	public static Date addSeconds(Date currentDate, int seconds) {
		return addDateTime(currentDate, Calendar.SECOND, seconds);
	}

	// ///////////////////////////////////////////////////////////////////////////////////

	// ///////////////////////////////////////////////////////////////////////////////////
	/**
	 * 生成 MS SQL SERVER 的日期字段的日期值
	 * 
	 */
	public static Date nowForMSSQLDateField() {
		return new Date();
	}

	// ///////////////////////////////////////////////////////////////////////////////////

	// ///////////////////////////////////////////////////////////////////////////////////
	/**
	 * 取 星期几
	 * 
	 * @param currentDate
	 *            当前日期
	 * @return 星期几
	 */
	public static synchronized String getWeekByDate(Date currentDate) {
		String week = "";
		if (currentDate != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);
			week = getWeek(calendar, true);
		}
		return week;
	}

	/**
	 * 取 星期几 ，具体取值
	 * 
	 * @param currentDate
	 *            当前日期
	 * @param toChinese
	 *            是否输出中文
	 * @return 星期几
	 */
	@SuppressWarnings("static-access")
	private static synchronized String getWeek(Calendar calendar, boolean toChinese) {
		String strRet = "";
		int intWeek = 0;

		/**
		 * 获取本周的第几天
		 */
		intWeek = calendar.get(calendar.DAY_OF_WEEK) - 1;
		if (!toChinese) {
			return (String.valueOf(intWeek));
		}
		switch (intWeek) {
		case 1: {
			strRet = "星期一";
			break;
		}
		case 2: {
			strRet = "星期二";
			break;
		}
		case 3: {
			strRet = "星期三";
			break;
		}
		case 4: {
			strRet = "星期四";
			break;
		}
		case 5: {
			strRet = "星期五";
			break;
		}
		case 6: {
			strRet = "星期六";
			break;
		}
		case 0: {
			strRet = "星期日";
			break;
		}
		default: {
			strRet = "不详";
			break;
		}
		}
		return strRet;
	}

	/**
	 * 获取两个 Date 相差的 天数
	 * 
	 * @param dateBeg
	 *            开始日期
	 * @param dateEnd
	 *            结束日期
	 * @return 两个 Date 相差的 天数
	 */
	public static long getDays(Date dateBeg, Date dateEnd) {
		if (dateEnd != null && dateEnd != null) {
			return (Math.abs(dateEnd.getTime() - dateBeg.getTime()) / 24 / 3600 / 1000);
		} else {
			return 0;
		}
	}

	/**
	 * 获取两个 Date 相差的 分钟数
	 * 
	 * @param dateBeg
	 *            开始日期
	 * @param dateEnd
	 *            结束日期
	 * @return 两个 Date 相差的 分钟数
	 */
	public static long getMinutes(Date dateBeg, Date dateEnd) {
		if (dateEnd != null && dateEnd != null) {
			return (Math.abs(dateEnd.getTime() - dateBeg.getTime()) / 60 / 1000);
		} else {
			return 0;
		}
	}

	/**
	 * 获取两个 Date 相差的 分钟数
	 * 
	 * @param dateBeg
	 *            开始日期
	 * @param dateEnd
	 *            结束日期
	 * @return 两个 Date 相差的 分钟数
	 */
	public static long getMinutes_No_abs(Date dateBeg, Date dateEnd) {
		if (dateEnd != null && dateEnd != null) {
			return ((dateEnd.getTime() - dateBeg.getTime()) / 60 / 1000);
		} else {
			return 0;
		}
	}

	/**
	 * 生成日期列表
	 * 
	 * @param dateBeg
	 *            起 日期
	 * @param dateEnd
	 *            止 日期
	 * @return 日期列表
	 */
	public static synchronized ArrayList<String> listDays(Date dateBeg, Date dateEnd) {
		ArrayList<String> listday = new ArrayList<String>();
		if (dateBeg != null && dateEnd != null) {
			long days = getDays(dateBeg, dateEnd);
			if (days > 0) {
				String dateBegStr = "";
				if (dateBeg.before(dateEnd)) {
					dateBegStr = convertDateTime_YYYYMMDD(dateBeg);
				} else {
					dateBegStr = convertDateTime_YYYYMMDD(dateEnd);
				}
				for (int i = 0; i < days; i++) {
					Date date = addDays(parseDateTime_YYYYMMDD(dateBegStr + " 00:00:00"), i);
					String dateBegNextDayStr = convertDateTime_YYYYMMDD(date);
					if (!listday.contains(dateBegNextDayStr)) {
						listday.add(dateBegNextDayStr);
					}
				}
			}
		}
		return listday;
	}

	/**
	 * 获取昨天日期时间
	 * 
	 * @return 获取24小时前的时间
	 * @author jh
	 */
	public static String getYestoday_YYYYMMDDHHMMSS() {
		GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
		gc.add(GregorianCalendar.DAY_OF_MONTH, -1);
		return convertDateTime_YYYYMMDDHHMMSS(gc.getTime());
	}

	/**
	 * 获取昨天日期
	 * 
	 * @return 获取24小时前的时间
	 * @author jh
	 */
	public static String getYestoday_YYYYMMDD() {
		GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
		gc.add(GregorianCalendar.DAY_OF_MONTH, -1);
		return convertDateTime_YYYYMMDD(gc.getTime());
	}

	/**
	 * 获取明天日期
	 * 
	 * @return 获取24小时后的时间
	 * @author jh
	 */
	public static String getTomorrow_YYYYMMDD() {
		GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
		gc.add(GregorianCalendar.DAY_OF_MONTH, 1);
		return convertDateTime_YYYYMMDD(gc.getTime());
	}

	/**
	 * 获取当前的分钟数据
	 * 
	 * @return 获取当前的分钟数据
	 * @author jh
	 */
	public static int getMinute(String date) {
		GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
		gc.setTime(parseDateTime_YYYYMMDDHHMMSS(date));
		return gc.get(GregorianCalendar.MINUTE);
	}

	/**
	 * 获取当前的分钟数据
	 * 
	 * @return 获取当前的分钟数据
	 * @author jh
	 */
	public static String getMinuteForString(String date) {
		int minute = getMinute(date);
		String minString = String.valueOf(minute);
		if (minute > 0 && minute < 10) {
			minString = "0" + minute;
		}
		return minString;
	}

	/**
	 * 初始化月份
	 * 
	 * @return
	 * @author jh
	 */
	public static String getCurrentYearToString() {
		GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
		gc.setTime(new Date());
		return String.valueOf(gc.get(GregorianCalendar.YEAR));
	}

	// 获取前一天日期 ,不包括hhmmss edit by king20130518
	public static Date getLastDate(Date starttime) {
		Date date = DateUtils.addDateTime(starttime, Calendar.DATE, -1);
		String dd = DateUtils.convertDateTime_YYYYMMDD(date);
		// String time=DateUtils.convertDateTime_HHMMSS(starttime);
		// dd+=" "+time;

		return parseDateTime_YYYYMMDD(dd);
	}

	/**
	 * 获得后一天
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static String getAfterDay(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayAfter;
	}

	/**
	 * 获取一周日期
	 * 
	 * @param mdate
	 * @return
	 */
	public static List<Date> dateToWeek(Date mdate) {
		Date fdate;
		int day = 0;
		List<Date> list = new ArrayList<Date>();
		if (mdate.getHours() >= 18) {
			// 超过18点，可以获取到第八天的
			day = 8;
		} else {
			day = 7;

		}
		Long fTime = mdate.getTime();
		for (int a = 1; a <= day; a++) {
			fdate = new Date();
			fdate.setTime(fTime + (a * 24 * 3600000));
			list.add(fdate);
		}
		return list;
	}

	public static List<String> getweek(Date mdate) {
		List<String> ret = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Date> week = dateToWeek(mdate);
		for (Date date : week) {
			ret.add(sdf.format(date));
		}

		return ret;
	}

	/**
	 * 创建默认的搜索时间
	 * 
	 * @author wms1231
	 * @param date
	 * @param isStart
	 * @return
	 */
	public static String getDefaultQueryTime(String date, boolean isStart) {
		if (StringUtils.isBlank(date)) {
			throw new RuntimeException("date日期字符串不能为空");
		}
		if (!date.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
			throw new RuntimeException("date日期字符串不满足默认的yyyy-MM-dd格式");
		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String[] dateArr = date.split("-");
		int year = Integer.valueOf(dateArr[0]);
		int month = Integer.valueOf(dateArr[1]);
		int day = Integer.valueOf(dateArr[2]);
		if (isStart) {
			calendar.set(year, month - 1, day, 00, 00, 00);
		} else {
			calendar.set(year, month - 1, day, 23, 59, 59);
		}

		return formatter.format(calendar.getTime());
	}

	public static void main(String[] args) {
		String defaultQueryTime = DateUtils.getDefaultQueryTime("2017-09-9", false);
		System.out.println(defaultQueryTime);
	}
}