package com.sanli.logic;

import javax.swing.JTextField;

public class TextFieldObject {

	private JTextField textField;
	private ValueType type;
	
	public TextFieldObject(JTextField textField, ValueType type){
		this.textField = textField;
		this.type = type;
	}
	
	public JTextField getVlaue() {
		return textField;
	}

	public void setVlaue(JTextField textField) {
		this.textField = textField;
	}
	
	

	public ValueType getType() {
		return type;
	}

	public void setType(ValueType type) {
		this.type = type;
	}



	public enum ValueType{
		INT,
		FLOAT,
		VARCHAR
	}
}
