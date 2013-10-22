package com.sanli.logic;

import javax.swing.JTextField;

public class TextFieldObject {

	private JTextField textField;
	private String name;
	
	public TextFieldObject(JTextField textField, String name){
		this.textField = textField;
		this.name = name;
	}
	
	public String getVlaue() {
		return textField.getText().trim();
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



}
