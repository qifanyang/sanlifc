package com.sanli.logic;

import javax.swing.JTextField;

public class TextFieldObject {

	private JTextField textField;
	private String name;
	
	public TextFieldObject(JTextField textField, String name){
		this.textField = textField;
		this.name = name;
	}
	
	/**
	 * ��ֵ����Ϊ0ʱ,����null,��ΪiBatis�в�ѯ������̬SQLʹ����nullֵ�������ѯ
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
