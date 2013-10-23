package com.sanli.logic;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * ������,�������ø�����
 */
public class Utils {
	
	/**
	 * �����ڸ�ʽΪYYYY-MM-DD���ַ���ת��Ϊ����,���Ҳ��long
	 * @param date 
	 * @return
	 */
	public static long dateToMillisecond(String date){
		if(date == null || date.length() == 0)return 0;
		String[] strings = date.split("-");
		if(strings.length != 3){
			throw new RuntimeException("����ת��ʧ�� , date = " + date);
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(strings[0]));
		//�·�ʹ��[0-11]��ʾʮ������
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
