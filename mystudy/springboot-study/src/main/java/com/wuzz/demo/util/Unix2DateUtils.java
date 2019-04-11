package com.wuzz.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间戳转化
 * @author MonkeyWen
 *
 */
public class Unix2DateUtils {

	public Unix2DateUtils() {
	}

	/**
	 * 获取将时间戳转化为相应格式的时间
	 * @param openTime
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date getUnix2Date(Long openTime, String pattern)  {
	       
		SimpleDateFormat format =  new SimpleDateFormat( pattern );

		Long time=new Long(openTime);

		String d = format.format(time);

		Date date = null;
		try {
			date = format.parse(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("时间转化错误");
		}
	
		return date ;
	}
}
