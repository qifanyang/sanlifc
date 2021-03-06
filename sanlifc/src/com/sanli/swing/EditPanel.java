package com.sanli.swing;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.sanli.logic.AppController;
import com.sanli.model.FCBean;

/**
 * 编辑面板显示的数据可能是从数据库加载也可能是从外部导入的,外部导入的数据不存库
 *
 */
public class EditPanel extends DataPanel {

	private static final long serialVersionUID = 1L;
	private static EditPanel instance = new EditPanel();
	private FCBean cache;
	
	public EditPanel(){
		super();
		JPanel btnPanel = new JPanel();
		JButton storeBtn = new JButton("保存修改...");
		JButton resetBtn = new JButton("撤销修改...");
		btnPanel.add(storeBtn);
		btnPanel.add(resetBtn);
		tc.fill = GridBagConstraints.CENTER;
		tc.gridy = 2;
		tc.gridx = 1;
		add(btnPanel, tc);
		
		
		storeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditDialog.getInstance().setVisible(false);
				boolean isRightFormat = AppController.getInstance().checkFormat(instance);
				if(!isRightFormat){
					return;
				}
				boolean success = AppController.getInstance().update(cache);
				if(!success) {
					AppWinUtils.showWarnMsg("更新数据失败");
				} else {
					AppWinUtils.showNormalMsg("更新数据成功");
//					ShowPanel.getInstance().showSelectResult(2);
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
