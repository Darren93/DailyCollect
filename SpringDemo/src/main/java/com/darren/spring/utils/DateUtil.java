package com.darren.spring.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {
	  public static final long SECOND = 1000;

	    public static final long MINUTE = SECOND * 60;

	    public static final long HOUR = MINUTE * 60;

	    public static final long DAY = HOUR * 24;

	    public static final long WEEK = DAY * 7;

	    public static final long YEAR = DAY * 365;

	    private static Log log = LogFactory.getLog(DateUtil.class);

	    public static final String DATATIME_PARTTERN_FILE = "yyyyMMddHHmmssS";
	    
	    public static final String PARTTERN_DATE = "yyyy-MM-dd";
	    
	    public static final String PARTTERN_SIMPLE_DATE = "yyyyMMdd";
	    
	    public static final String PARTTERN_FULL_DATETIME = "yyyy-MM-dd HH:mm:ss";
	    
	    public static final String PARTTERN_BLANK_SECOND = "yyyy-MM-dd HH:mm";
	    
	    public static final String PARTTERN_DEVIANT_BLANK_SECOND = "yyyy-MM-dd:HH:mm";
	    
//	    private static DateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

	    /**
	     * 解析日期
	     * 
	     * @param date
	     *            日期字符串
	     * @param pattern
	     *            日期格式
	     * @return
	     */
	    public static Date parse(String date, String pattern) {
	        Date resultDate = null;
	        try {
	            resultDate = new SimpleDateFormat(pattern).parse(date);
	        } catch (ParseException e) {
	            log.error(e);
	        }
	        return resultDate;
	    }
	 
	    /**
	     * 解析日期
	     * 
	     * @param date
	     *            日期字符串
	     * @param pattern
	     *            日期格式
	     * @return
	     */
	    public static Date parse(Date date, String pattern) {
	        Date resultDate = null;
	        try {
	            resultDate = new SimpleDateFormat(pattern).parse(DateUtil.format(date, pattern));
	        } catch (ParseException e) {
	            log.error(e);
	        }
	        return resultDate;
	    }
	    

	    /**
	     * 解析日期 yyyy-MM-dd HH:mm:ss
	     * 
	     * @param date
	     *            日期字符串
	     * @param pattern
	     *            日期格式
	     * @return
	     */
	    public static Date parseSimple(String date) {
	        Date resultDate = null;
	        DateFormat format  = new SimpleDateFormat("yyyy-MM-dd");
	        try {
	            resultDate = format.parse(date);
	        } catch (ParseException e) {
	            log.error(e);
	        }
	        return resultDate;
	    }
	    
	    public static String parseStrDate(String strDate) {
			SimpleDateFormat smf  = new SimpleDateFormat("yyyyMMddHHmmss");
			SimpleDateFormat smf2  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String resultDate = "";
			try {
				resultDate = smf2.format(smf.parse(strDate));
			} catch (ParseException e) {
				log.error(e);
			}
	        return resultDate;
	    }

	    /**
	     * 格式化日期字符串
	     * 
	     * @param date
	     *            日期
	     * @param pattern
	     *            日期格式
	     * @return
	     */
	    public static String format(Date date, String pattern) {
	        DateFormat format = new SimpleDateFormat(pattern);
	        return format.format(date);
	    }

	    /**
	     * 取得当前日期
	     * 
	     * @return
	     */
	    public static Date getCurrentDate() {
	        return new Date(System.currentTimeMillis());
	    }
	    
	    /**
	     * 取得当前日期
	     * 
	     * @return
	     */
	    public static Timestamp getCurrentTimestamp() {
	        return new Timestamp(System.currentTimeMillis());
	    }

	    /**
	     * @param offsetYear
	     * @return 当前时间 + offsetYear
	     */
	    public static Timestamp getTimestampExpiredYear(int offsetYear) {
	        Calendar now = Calendar.getInstance();
	        now.add(Calendar.YEAR, offsetYear);
	        return new Timestamp(now.getTime().getTime());
	    }

	    /**
	     * @param offsetMonth
	     * @return 当前时间 + offsetMonth
	     */
	    public static Timestamp getCurrentTimestampExpiredMonth(int offsetMonth) {
	        Calendar now = Calendar.getInstance();
	        now.add(Calendar.MONTH, offsetMonth);
	        return new Timestamp(now.getTime().getTime());
	    }

	    /**
	     * @param offsetDay
	     * @return 当前时间 + offsetDay
	     */
	    public static Timestamp getCurrentTimestampExpiredDay(int offsetDay) {
	        Calendar now = Calendar.getInstance();
	        now.add(Calendar.DATE, offsetDay);
	        return new Timestamp(now.getTime().getTime());
	    }

	    /**
	     * @param offsetSecond
	     * @return 当前时间 + offsetSecond
	     */
	    public static Timestamp getCurrentTimestampExpiredSecond(int offsetSecond) {
	        Calendar now = Calendar.getInstance();
	        now.add(Calendar.SECOND, offsetSecond);
	        return new Timestamp(now.getTime().getTime());
	    }

	    /**
	     * @param offsetDay
	     * @return 指定时间 + offsetDay
	     */
	    public static Timestamp getTimestampExpiredDay(Date givenDate, int offsetDay) {
	        Calendar date = Calendar.getInstance();
	        date.setTime(givenDate);
	        date.add(Calendar.DATE, offsetDay);
	        return new Timestamp(date.getTime().getTime());
	    }

	    /**
	     * 实现ORACLE中ADD_MONTHS函数功能
	     * 
	     * @param offsetMonth
	     * @return 指定时间 + offsetMonth
	     */
	    public static Timestamp getTimestampExpiredMonth(Date givenDate, int offsetMonth) {
	        Calendar date = Calendar.getInstance();
	        date.setTime(givenDate);
	        date.add(Calendar.MONTH, offsetMonth);
	        return new Timestamp(date.getTime().getTime());
	    }

	    /**
	     * @param offsetSecond
	     * @return 指定时间 + offsetSecond
	     */
	    public static Timestamp getTimestampExpiredSecond(Date givenDate, int offsetSecond) {
	        Calendar date = Calendar.getInstance();
	        date.setTime(givenDate);
	        date.add(Calendar.SECOND, offsetSecond);
	        return new Timestamp(date.getTime().getTime());
	    }
	    
	    /**
	     * @param offsetSecond
	     * @return 指定时间 + offsetSecond
	     */
	    public static Timestamp getTimestampExpiredHour(Date givenDate, int offsetHour) {
	        Calendar date = Calendar.getInstance();
	        date.setTime(givenDate);
	        date.add(Calendar.HOUR, offsetHour);
	        return new Timestamp(date.getTime().getTime());
	    }

	    /**
	     * @return 当前日期 yyyy-MM-dd
	     */
	    public static String getCurrentDay() {
	        return DateUtil.format(new Date(), "yyyy-MM-dd");
	    }

	    /**
	     * @return 当前的时间戳 yyyy-MM-dd HH:mm:ss
	     */
	    public static String getNowTime() {
	        return DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss");
	    }

	    /**
	     * @return 给出指定日期的月份的第一天
	     */
	    public static Date getMonthFirstDay(Date givenDate) {
	        Date date = DateUtil.parse(DateUtil.format(givenDate, "yyyy-MM"), "yyyy-MM");
	        return date;
	    }

	    /**
	     * @return 给出指定日期的月份的最后一天
	     */
	    public static Date getMonthLastDay(Date givenDate) {
	        Date firstDay = getMonthFirstDay(givenDate);
	        Date lastMonthFirstDay = DateUtil.getTimestampExpiredMonth(firstDay, 1);
	        Date lastDay = DateUtil.getTimestampExpiredDay(lastMonthFirstDay, -1);
	        return lastDay;
	    }

	    /**
	     * 当前时间加offsetMinut分钟
	     * @param offsetMinut
	     * @return 当前时间 + offsetMinut
	     */
	    public static Date getCurrentDateExpiredMinut(int offsetMinut) {
	        Calendar rightNow = Calendar.getInstance();
	        rightNow.add(Calendar.MINUTE, offsetMinut);
	        return rightNow.getTime();
	    }
	    
	    /**
	     * 两日期差多少天
	     * @param start
	     * @param end
	     * @return
	     */
	    public static int diffOfDay(Date start, Date end){
	    	return (int)((end.getTime() - start.getTime()) / DateUtil.DAY);
	    }
	    
	    /**
	     * 判断两个时间是否跨月
	     * @param start
	     * @param end
	     * @return true：表示跨月， false：表示同月
	     */
	    public static boolean strideOfMonth(Date start, Date end){
	    	Calendar scalendar = Calendar.getInstance();
	    	scalendar.setTime(start);
	    	
	    	Calendar ecalendar = Calendar.getInstance();
	    	ecalendar.setTime(end);
	    	
			return !(scalendar.get(Calendar.YEAR) == ecalendar.get(Calendar.YEAR)
					&& scalendar.get(Calendar.MONTH) == ecalendar.get(Calendar.MONTH));
	    }
	    
	    public static int getMonthOfDay(Date date){
	    	Calendar calendar = Calendar.getInstance();
	    	calendar.setTime(date);
	    	calendar.set(Calendar.DATE, 1);//把日期设置为当月第一天  
	    	calendar.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
	    	return calendar.get(Calendar.DATE);
	    }
	    
	    public static boolean between(Date start, Date mid, Date end){
	    	if(start.after(end))
	    		return false;
	    	
	    	Calendar scalendar = Calendar.getInstance();
	    	scalendar.setTime(start);
	    	
	    	Calendar mcalendar = Calendar.getInstance();
	    	mcalendar.setTime(mid);
	    	
	    	Calendar ecalendar = Calendar.getInstance();
	    	ecalendar.setTime(end);
	    	if(scalendar.get(Calendar.YEAR) > mcalendar.get(Calendar.YEAR))
	    		return false;
	    	
	    	if(scalendar.get(Calendar.YEAR) <= mcalendar.get(Calendar.YEAR)
	    	&& scalendar.get(Calendar.MONTH) > mcalendar.get(Calendar.MONTH))
	    		return false;
	    	
	    	if(ecalendar.get(Calendar.YEAR) < mcalendar.get(Calendar.YEAR))
	    			return false;
	    	if(ecalendar.get(Calendar.YEAR) >= mcalendar.get(Calendar.YEAR)
	    	&& ecalendar.get(Calendar.MONTH) < mcalendar.get(Calendar.MONTH))
	    		return false;    	
	    		
	    	return true;
	    }
	    
	    /**
	     * 接收时间，返回距当前时间是多少，用于评论渲染
	     * @param dateStr 输入时间字符
	     * @return
	     * 
	     */
	    public static String getDateStringFromDate(String dateStr){
	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    	Long end=DateUtil.getCurrentTimestamp().getTime();
	    	Long start=Timestamp.valueOf(dateStr).getTime();
	    	String dateResult="";
	    	Long diffMin=(Long)((end - start) / DateUtil.MINUTE);
	    	Long diffHour=(Long)(end-start)/DateUtil.HOUR;
	    	Long diffDay=(Long)(end-start)/DateUtil.DAY;
	    	if(diffMin>=0 && diffMin<=5 ){
	    		dateResult="刚刚";
	    	}else if(diffMin>5 && diffMin<60){
	    		dateResult=diffMin+"分钟前";
	    	}else if(diffHour>=1 && diffHour<24){
	    		dateResult=diffHour+"小时前";
	    	}else if(diffDay>=1 &&diffDay<=3){
	    		dateResult=diffDay+"天前";
	    	}else{
	    		try {
					dateResult=sdf.format(sdf.parse(dateStr));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	return dateResult;
	    }
}
