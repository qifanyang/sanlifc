package com.sanli.logic;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 工具类,不单独用个包了
 */
public class Utils {
	
	/**
	 * 将日期格式为YYYY-MM-DD的字符串转换为毫秒,存库也用long
	 * @param date 
	 * @return
	 */
	public static long dateToMillisecond(String date){
		if(date == null || date.length() == 0)return 0;
		String[] strings = date.split("-");
		if(strings.length != 3){
			throw new RuntimeException("日期转换失败 , date = " + date);
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(strings[0]));
		//月份使用[0-11]表示十二个月
		calendar.set(Calendar.MONTH, Integer.parseInt(strings[1]) - 1);
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(strings[2]));
		
		return calendar.getTimeInMillis();
	}
	
	public static String millisecondToDate(long timeMillis){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timeMillis);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(calendar.getTime());
		 
	}
	
	public static void main(String[] args) {
		long dateToLong = dateToMillisecond("2012-1-25");
		System.out.println(dateToLong);
		System.out.println(millisecondToDate(dateToLong));
		System.out.println(dateToMillisecond(millisecondToDate(dateToLong)));
		
	}

}
