package com.sanli.model;

import java.lang.reflect.Field;


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
	
	public void setValue(String name, String value){
		try {
			Field field = getClass().getField(name);
			Class<?> type = field.getType();
			if(type == int.class){
				field.setInt(this, Integer.parseInt(value));
			}else if(type == long.class){
				field.set(this, Long.parseLong(value));
			}else if(type == float.class){
				field.set(this, Float.parseFloat(value));
			}else if(type == String.class){
				field.set(this, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		System.out.println(new Date(System.currentTimeMillis()));
//		FCBean fcBean = new FCBean();
//		Field[] fields = fcBean.getClass().getFields();
//		StringBuffer buf = new StringBuffer();
//		for(Field f : fields){
//			buf.append(f.getName()).append(", ");
//		}
//		System.out.println(buf.toString());
//		buf.setLength(0);
//		for(Field f : fields){
//			buf.append("#{").append(f.getName()).append("}").append(", ");
//		}
//		System.out.println(buf.toString());
//	}
}
