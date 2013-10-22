package com.sanli.logic;

import javax.swing.JTextField;

public class TextFieldObject {

	private JTextField textField;
	private String name;
	
	public TextFieldObject(JTextField textField, String name){
		this.textField = textField;
		this.name = name;
	}
	
	public JTextField getVlaue() {
		return textField;
	}

	public void setVlaue(JTextField textField) {
		this.textField = textField;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}
