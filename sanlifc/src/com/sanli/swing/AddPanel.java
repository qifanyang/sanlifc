package com.sanli.swing;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sanli.logic.AppController;
import com.sanli.model.FCBean;
import com.sanli.model.TextFieldObject;

/**
 * 
 *�����������,��ParaPanelһ��,���︴�ƴ���ParaPanel�Ĵ����޸�,
 *������Բ��ü̳��Ż�����
 */
public class AddPanel extends DataPanel{
	private final static Log log = LogFactory.getLog(ParaPanel.class);

	private static final long serialVersionUID = 1L;
	
	private static AddPanel instance = new AddPanel();
	
	private AddPanel(){
		super();
		JPanel btnPanel = new JPanel();
		JButton selectBtn = new JButton("ȷ�����...");
		JButton resetBtn = new JButton("������д...");
		btnPanel.add(selectBtn);
		btnPanel.add(resetBtn);
		tc.fill = GridBagConstraints.CENTER;
		tc.gridy = 2;
		tc.gridx = 1;
		add(btnPanel, tc);
		
		selectBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//ִ�в�ѯ,���������ѯ�������ʾ��ѯ���
				log.info("action add data......");
				log.info("check repeat id......");
				
				boolean isRightFormat = AppController.getInstance().checkFormat(instance);
				if(!isRightFormat){
					return;
				}
				
				if(AppController.getInstance().getInsertFCBean().isNull()){
					AppWinUtils.showWarnMsg("����д����,���ܲ��������!");
					return ;
				}
				//id�����ظ�,���ݿ����潨����Ψһ����
				FCBean bean = AppController.getInstance().checkInsertAble();
				if(bean != null){
//					JOptionPane.showMessageDialog(ToolUI.getIntance(), "����[��� :"+bean.id+" ]�ظ�,��������ظ������,���޸�...!", "����", JOptionPane.WARNING_MESSAGE);
					AppWinUtils.showWarnMsg("����[��� :"+bean.id+" ]�ظ�,��������ظ������,���޸�...!");
					return;
				}
				
				
				boolean success = AppController.getInstance().insert();
				//
				if(!success){
					AppWinUtils.showWarnMsg("�������ʧ��");
				}else{
					AppWinUtils.showNormalMsg("������ݳɹ�");
				}
			}
		});
		
		resetBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(TextFieldObject tfo : vlist){
					tfo.setVlaue("");
				}
				
			}
		});
	}
	
	
	public static AddPanel getInstance(){
		return instance;
	}

}
