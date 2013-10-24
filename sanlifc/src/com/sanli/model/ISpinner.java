package com.sanli.model;

import javax.swing.JSpinner;

public class ISpinner extends JSpinner implements UIData {

	private static final long serialVersionUID = 1L;

	@Override
	public String getString() {
		return null;
	}

	@Override
	public void setString(String value) {
	}

	@Override
	public int getInt() {
		return Integer.parseInt(super.getValue().toString());
	}

	@Override
	public void setInt(int value) {
		setValue(value);
	}

}
