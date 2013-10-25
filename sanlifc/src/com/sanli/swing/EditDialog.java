package com.sanli.swing;

import javax.swing.JDialog;


public class EditDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private static EditDialog inntance = new EditDialog();
	
	private EditDialog(){
		
	}
	
	public static EditDialog getIntance(){
		return inntance;
	}

}
