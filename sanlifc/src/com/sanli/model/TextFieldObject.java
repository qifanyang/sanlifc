package com.sanli.model;

import javax.swing.JTextField;

public class TextFieldObject {

	private JTextField textField;
	private String name;
	
	public TextFieldObject(JTextField textField, String name){
		this.textField = textField;
		this.name = name;
	}
	
	/**
	 * 当值长度为0时,返回null,因为iBatis中查询构建动态SQL使用了null值来构造查询
	 * @return
	 */
	public String getVlaue() {
		String ss = textField.getText().trim();
		return ss.length() == 0 ? null : ss;
	}
	

	public void setVlaue(String value) {
		this.textField.setText(value);
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JTextField getTextField(){
		return textField;
	}


}
