package com.sanli.swing;

import java.awt.Toolkit;
import java.util.List;

import javax.swing.JDialog;

import com.sanli.logic.AppController;
import com.sanli.model.FCBean;


public class EditDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private static EditDialog inntance = new EditDialog();
	
	private EditPanel editPanel = EditPanel.getInstance();
	
	private EditDialog(){
		add(editPanel);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width, 400);
		setLocationRelativeTo(null);
		setTitle("修改数据");
	}
	
	public static EditDialog getIntance(){
		return inntance;
	}
	
	public void showEditDialog(){
		//使用bean填充面板
		List<FCBean> tmpList = AppController.getInstance().getTmpList();
		if(tmpList.size() > 0){
			FCBean bean = tmpList.get(0);
			try {
				editPanel.fillData(bean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		setModal(true);
		setVisible(true);
	}

}
