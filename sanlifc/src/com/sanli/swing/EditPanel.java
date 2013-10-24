package com.sanli.swing;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.sanli.logic.AppController;
import com.sanli.logic.AppWinUtils;
import com.sanli.logic.Utils;
import com.sanli.model.FCBean;

public class EditPanel extends DataPanel {

	private static final long serialVersionUID = 1L;
	private static EditPanel instance = new EditPanel();
	private FCBean cache;
	
	public EditPanel(){
		super();
		JPanel btnPanel = new JPanel();
		JButton storeBtn = new JButton("�����޸�...");
		JButton resetBtn = new JButton("�����޸�...");
		btnPanel.add(storeBtn);
		btnPanel.add(resetBtn);
		tc.fill = GridBagConstraints.CENTER;
		tc.gridy = 2;
		tc.gridx = 1;
		add(btnPanel, tc);
		
		
		storeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean success = AppController.getInstance().update();
				EditDialog.getIntance().dispose();
				if(!success) {
					AppWinUtils.showWarnMsg("��������ʧ��");
				} else {
					AppWinUtils.showNormalMsg("�������ݳɹ�");
				}
			}
		});
		
		resetBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					fillData(cache);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
	}
	
	public static EditPanel getInstance() {
		return instance;
	}

	@Override
	public void fillData(FCBean bean) throws Exception {
		super.fillData(bean);
		this.cache = bean;
	}
}
