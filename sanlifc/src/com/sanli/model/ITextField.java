package com.sanli.model;

import javax.swing.JTextField;

public class ITextField extends JTextField implements UIData {

	private static final long serialVersionUID = 1L;
	
	public ITextField(){};
	
	public ITextField(String value){
		super(value);
	};

	@Override
	public String getString() {
		return this.getText();
	}

	@Override
	public void setString(String value) {
		this.setText(value);
	}

	@Override
	public int getInt() {
		return 0;
	}

	@Override
	public void setInt(int value) {

	}

}
