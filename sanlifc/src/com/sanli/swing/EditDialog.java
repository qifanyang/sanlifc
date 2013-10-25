package com.sanli.swing;

import java.awt.Toolkit;
import java.util.List;

import javax.swing.JDialog;

import com.sanli.dao.DataServer;
import com.sanli.logic.AppController;
import com.sanli.model.FCBean;


public class EditDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private static EditDialog inntance = new EditDialog();
	
	private EditPanel editPanel = EditPanel.getInstance();
	
	private EditDialog(){
		add(editPanel);
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width, 400);
		setLocationRelativeTo(null);
		setTitle("�޸�����");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		setModal(true);
	}
	
	public static EditDialog getInstance(){
		return inntance;
	}
	
	public void showEditDialog(FCBean bean){
		//ʹ��bean������
		List<FCBean> tmpList = AppController.getInstance().getTmpList();
		if(tmpList.size() > 0 && bean == null){
			 bean = tmpList.get(0);
		}
		if(bean != null){
			bean = DataServer.getInstance().selectOne(bean.id);
		}
		try {
			editPanel.fillData(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
