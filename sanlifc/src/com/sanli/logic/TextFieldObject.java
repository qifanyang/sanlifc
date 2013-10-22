package com.sanli.logic;

public class TextFieldObject {

	private String vlaue;
	
	
	
	public String getVlaue() {
		return vlaue;
	}

	public void setVlaue(String vlaue) {
		this.vlaue = vlaue;
	}

	public enum ValueType{
		FLOAT,
		VARCHAR
	}
}
