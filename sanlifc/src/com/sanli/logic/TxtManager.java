package com.sanli.logic;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sanli.model.FCBean;

/**
 * 文本文件的加载和导出
 *
 * @author XF
 * 2013-10-22 下午10:59:01
 */
public class TxtManager {
	
	private static TxtManager instance = new TxtManager();
	
	public static TxtManager getInstance(){
		return instance;
	}
	
	public  void export(String path, List<FCBean> list) throws Exception{
		StringBuilder builder = new StringBuilder();
		for(FCBean bean : list){
			Field[] fields = bean.getClass().getFields();
			for(Field f : fields){
				if(!f.getName().equalsIgnoreCase("uuid")){
					Class<?> type = f.getType();
					if(type == int.class){
						builder.append(f.getInt(bean)).append("\t");
					}else if(type == long.class){
						builder.append(f.getLong(bean)).append("\t");
					}else if(type == float.class){
						builder.append(f.getFloat(bean)).append("\t");
					}else if(type == String.class){
						builder.append(f.get(bean)).append("\t");
					}
				}
			}
			builder.append("\n");
		}
		
		System.out.println(builder.toString());
	}
	
	
	public static void main(String[] args) throws Exception {
		FCBean fcBean = new FCBean();
		fcBean.id = 1;
		fcBean.city = "nihao";
		fcBean.year = 2013;
		fcBean.check_info = "wowowowowoowwoo";
		
		ArrayList<FCBean> list = new ArrayList<FCBean>();
		list.add(fcBean);
		
		getInstance().export(null, list);
	}
	
	
}
