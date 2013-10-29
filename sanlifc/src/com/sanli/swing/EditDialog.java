package com.sanli.swing;

import java.awt.Toolkit;

import javax.swing.JDialog;

import com.sanli.model.FCBean;


public class EditDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private static EditDialog inntance = new EditDialog();
	
	private EditPanel editPanel = EditPanel.getInstance();
	
	private EditDialog(){
		add(editPanel);
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width, 400);
		setLocationRelativeTo(null);
		setTitle("ÐÞ¸ÄÊý¾Ý");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		setModal(true);
	}
	
	public static EditDialog getInstance(){
		return inntance;
	}
	
	public void showEditDialog(FCBean bean){
		
		try {
			editPanel.fillData(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
