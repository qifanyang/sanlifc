package com.sanli.swing;

import java.awt.Toolkit;

import javax.swing.JDialog;

import com.sanli.model.FCBean;

public class EditDialog2 extends JDialog{
private static final long serialVersionUID = 1L;
	
	private static EditDialog2 inntance = new EditDialog2();
	
	private EditPanel2 editPanel = EditPanel2.getInstance();
	
	private EditDialog2(){
		add(editPanel);
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width, 400);
		setLocationRelativeTo(null);
		setTitle("ÐÞ¸ÄÊý¾Ý");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		setModal(true);
	}
	
	public static EditDialog2 getInstance(){
		return inntance;
	}
	
	public void showEditDialog(FCBean bean , int row){
		editPanel.row = row;
		try {
			editPanel.fillData(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
