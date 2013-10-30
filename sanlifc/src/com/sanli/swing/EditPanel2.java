package com.sanli.swing;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.sanli.logic.AppController;
import com.sanli.model.FCBean;
import com.sanli.model.TextFieldObject;

/**批量修改面板*/
public class EditPanel2 extends DataPanel{
	private static final long serialVersionUID = 1L;
	private static EditPanel2 instance = new EditPanel2();
	private FCBean cache;
	public int row;
	public EditPanel2(){
		super();
		JPanel btnPanel = new JPanel();
		JButton storeBtn = new JButton("确定修改...");
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
				EditDialog2.getInstance().setVisible(false);
				boolean isRightFormat = AppController.getInstance().checkFormat(instance);
				if(!isRightFormat){
					return;
				}
//				boolean success = AppController.getInstance().update(cache);
				List<TextFieldObject> vList = EditPanel2.getInstance().getVList();
//				FCBean fcBean = new FCBean();
				for(TextFieldObject tfo : vList){
					cache.setValue(tfo.getName(), tfo.getVlaue());
				}
				
				BatchPanel.getInstance().updateBean(cache.getCopy(), row);
				BatchPanel.getInstance().showInTable(BatchPanel.getInstance().beanList);
				
//				if(!success) {
//					AppWinUtils.showWarnMsg("更新数据失败");
//				} else {
//					AppWinUtils.showNormalMsg("更新数据成功");
//				}
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
	
	public static EditPanel2 getInstance() {
		return instance;
	}

	@Override
	public void fillData(FCBean bean) throws Exception {
		super.fillData(bean);
		this.cache = bean;
	}
}
