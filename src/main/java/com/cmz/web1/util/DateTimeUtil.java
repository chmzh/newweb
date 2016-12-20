package com.cmz.web1.util;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.GJChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
/**
 * 时区参考 http://www.joda.org/joda-time/timezones.html
 * @author chenmingzhou
 *
 */
public class DateTimeUtil {
	public final static String pattern = "yyyy-MM-dd hh:mm:ss";
	/**
	 * 当前时间
	 * @param timezone
	 * @return
	 */
	public static String curDate(String timezone){
		//DateTimeZone zone = DateTimeZone.forID("Asia/Tokyo");
		DateTimeZone zone = DateTimeZone.forID(timezone);
		Chronology gregorianJuian = GJChronology.getInstance(zone);
		DateTime dt = new DateTime(gregorianJuian);
		DateTimeFormatter format = DateTimeFormat.forPattern(pattern);
		return dt.toString(format);
	}
	/**
	 * 默认返回 Asia/Shanghai 时区时间
	 * @return
	 */
	public static String curDate(){
		return curDate("Asia/Shanghai");
	}
	
	public static void main(String[] args) {
		System.out.println(curDate());
	}
}
