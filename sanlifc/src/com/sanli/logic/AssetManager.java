package com.sanli.logic;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.sanli.model.FCBean;

/**
 * 文本文件的加载和导出
 *
 * @author XF
 * 2013-10-22 下午10:59:01
 */
public class AssetManager {
	
	private static AssetManager instance = new AssetManager();
	
	public static AssetManager getInstance(){
		return instance;
	}
	
	public  boolean export(String path, List<FCBean> list) throws Exception{
		StringBuilder builder = new StringBuilder();
		for(FCBean bean : list){
			Field[] fields = bean.getClass().getFields();
			for(Field f : fields){
				if(!f.getName().equalsIgnoreCase("uuid")){
					Class<?> type = f.getType();
					if(type == int.class){
						builder.append(f.getInt(bean)).append("\t");
					}else if(type == long.class){
						builder.append(Utils.millisecondToDate(f.getLong(bean))).append("\t");
					}else if(type == float.class){
						builder.append(f.getFloat(bean)).append("\t");
					}else if(type == String.class){
						Object obj = f.get(bean);
						if(obj == null){
							builder.append("\t");
						}else{
							builder.append(f.get(bean)).append("\t");
						}
					}
				}
			}
			builder.append("\n");
		}
		//
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
		writer.write(builder.toString());
		writer.flush();
		writer.close();
		System.out.println(builder.toString());
		return true;
	}
	
	
	public static void main(String[] args) throws Exception {
		FCBean fcBean = new FCBean();
		fcBean.id = 1;
		fcBean.city = "nihao";
		fcBean.year = 2013;
		fcBean.check_info = "验收情况";
		fcBean.remark = "备注一";
		FCBean fcBean1 = new FCBean();
		fcBean1.id = 1;
		fcBean1.city = "成都";
		fcBean1.year = 2013;
		fcBean1.check_info = "验收情况222";
		fcBean1.remark = "备注一";
		
		
		ArrayList<FCBean> list = new ArrayList<FCBean>();
		list.add(fcBean);
		list.add(fcBean1);
		
		getInstance().export(null, list);
	}
	
	
}
