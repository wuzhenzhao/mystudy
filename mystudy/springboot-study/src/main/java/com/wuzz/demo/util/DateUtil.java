package com.wuzz.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	/**
	 * 获取当前时间
	 * 格式：yyyyMMddHHmmss
	 * @param pattern
	 * @return
	 */
	public static String getCurrentDateTime(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);  
		String datetime = sdf.format(new Date()); 
		return datetime ;
	}
	
	/**
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getDateTime(Date date , String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);  
		String datetime = sdf.format(date); 
		return datetime ;
	}

}
