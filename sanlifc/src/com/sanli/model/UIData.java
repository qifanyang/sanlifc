package com.sanli.model;

/**
 * 可以编辑的控件都应实现该接口,用于可编辑控件的数据访问.
 * 主要是JTextFeild控件和JSpinner...等等
 */
public interface UIData {

	public String getString();
	
	public void setString(String value);
	
	public int getInt();
	
	public void setInt(int value);
}
