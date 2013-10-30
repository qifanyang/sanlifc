package com.sanli.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.sanli.swing.ToolUI;

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
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			return dateFormat.parse(date).getTime();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return 0;
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(strings[0]));
		//月份使用[0-11]表示十二个月
		calendar.set(Calendar.MONTH, Integer.parseInt(strings[1]) - 1);
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(strings[2]));
		return calendar.getTimeInMillis();
	}
	
	public static String millisecondToDate(long timeMillis){
		if(timeMillis <= 0)return "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timeMillis);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(calendar.getTime());
		 
	}
	
	public static void showMsg(String info, String title){
		JOptionPane.showMessageDialog(ToolUI.getIntance(), info, title, JOptionPane.WARNING_MESSAGE);
	}
	
	
	public static boolean isNumeric(String s){
		Pattern pattern = Pattern.compile("^[0-9]*[.]?[0-9]*$");
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}
	
	/**
	 * yyyy-MM-dd
	 * @param s
	 * @return
	 */
	public static boolean isDate(String s){
		Pattern pattern = Pattern.compile("^[0-9]{4}[-]{1}[0-9]{1,2}[-]{1}[0-9]{1,2}$");
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}
	
	
	public static void sleep(long millis){
		try {
			Thread.sleep(millis);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		long dateToLong = dateToMillisecond("2012-1-25");
		System.out.println(dateToLong);
		System.out.println(millisecondToDate(dateToLong));
		System.out.println(dateToMillisecond(millisecondToDate(dateToLong)));
		
		System.out.println("=================");
		String s = "223.1";
		System.out.println(isNumeric(s));
		
		String date = "2012-57-74";
		System.out.println(isDate(date));
		
		SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
		System.out.println(format.format(Calendar.getInstance().getTime()));
		
	}

}
