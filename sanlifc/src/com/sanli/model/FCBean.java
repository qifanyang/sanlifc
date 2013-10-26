package com.sanli.model;

import java.lang.reflect.Field;
import com.sanli.swing.AppWinUtils;
import com.sanli.util.LanguageLoader;
import com.sanli.util.Utils;


/**
 * 对应数据库表的实体类
 */
public class FCBean {
	public int uuid;
	public int id;
	public String city;
	public int year;
	public String create_project_title;
	public String create_project_number;
	public String project_name;
	public String mis_number;
	public String research_contract_number;
	public String simple_name;
	public String project_main_type;
	public String project_type;
	public String create_project_info;
	public String a_project_manager;
	public String project_leader;
	public String contract_pay_info;
	public float project_total_invest;
	public float ht_wireless;
	public float ht_transmission;
	public float ht_power;
	public float ht_civil;
	public float ht_total;
	public long a_time;
	public String a_note;
	public long b_time;
	public String b_note;
	public long c_time;
	public String c_note;
	public long d_time;
	public String d_note;
	public long e_time;
	public String e_note;
	public long f_time;
	public String f_note;
	public String check_info;
	public long final_cost_time;
	public float final_wireless;
	public float final_transmission;
	public float final_power;
	public float final_civil;
	public float final_total;
	public long fapiao_a_time;
	public float fapiao_a_scale;
	public float fapiao_a_money;
	public long fapiao_b_time;
	public float fapiao_b_scale;
	public float fapiao_b_money;
	public long fapiao_c_time;
	public float fapiao_c_scale;
	public float fapiao_c_money;
	public String remark;
	
	/**
	 * 成功返回0,失败返回1
	 * @param name
	 * @param value
	 * @return
	 */
	@SuppressWarnings("null")
	public int setValue(String name, String value){
		try {
			Field field = getClass().getField(name);
			Class<?> type = field.getType();
			if(type == int.class){
				if(value != null && value.length() > 0){
					if(!Utils.isNumeric(value)){
						AppWinUtils.showWarnMsg(LanguageLoader.getInstance().getUIName(name) + "填写不正确, 只能填写数字");
						return 1;
					}
					field.setInt(this, Integer.parseInt(value));
				}else{
					field.setInt(this, 0);
				}
			}else if(type == long.class){
				if(value != null && value.length() > 0){
					if(!Utils.isDate(value)){
						AppWinUtils.showWarnMsg(LanguageLoader.getInstance().getUIName(name) + " 填写不正确, 格式 YYYY-MM-DD \n    例如 :2013-5-12");
						return 1;
					}
					field.setLong(this, Utils.dateToMillisecond(value));
				}else{
					field.setLong(this, 0);
				}
				
			}else if(type == float.class){
				if(value != null && value.length() > 0){
					if(!Utils.isNumeric(value)){
						AppWinUtils.showWarnMsg(LanguageLoader.getInstance().getUIName(name) + " 填写不正确, 只能填写数字");
						return 1;
					}
					field.set(this, Float.parseFloat(value));
				}else{
					field.set(this, 0);
				}
//				field.set(this, value == null || value.length() ==0 ? 0 : Float.parseFloat(value));
			}else if(type == String.class){
				field.set(this, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 检查Bean是不是为空,当所有数值为0,String长度为0,则表明该Bean为Null,不可以保存到数据库
	 * @return
	 */
	public boolean isNull(){
		boolean isNull = true;
		try {
			Field[] fields = this.getClass().getFields();
			for(Field f : fields){
				if(!f.getName().equalsIgnoreCase("uuid")){
					Class<?> type = f.getType();
					if(type == int.class){
						isNull = f.getInt(this) != 0 ? false :  true;
					}else if(type == long.class){
						isNull = f.getLong(this) != 0 ? false :  true;
					}else if(type == float.class){
						isNull = f.getFloat(this) != 0 ? false :  true;
					}else if(type == String.class){
						isNull = f.get(this) == null || ((String)f.get(this)).length() <= 0 ? true :  false;
					}
				}
				if(!isNull){
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isNull;
	}
	public static void main(String[] args) {
//		System.out.println(new Date(System.currentTimeMillis()));
		FCBean fcBean = new FCBean();
		Field[] fields = fcBean.getClass().getFields();
		StringBuffer buf = new StringBuffer();
		for(Field f : fields){
			buf.append(f.getName()).append("=\n");
		}
		System.out.println(buf.toString());
//		buf.setLength(0);
//		for(Field f : fields){
//			buf.append("#{").append(f.getName()).append("}").append(", ");
//		}
//		System.out.println(buf.toString());
	}
}
