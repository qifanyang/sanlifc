package com.sanli.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sanli.logic.AppController;
import com.sanli.logic.TextFieldObject;
import com.sanli.logic.Utils;
import com.sanli.logic.AppWinUtils;
import com.sanli.model.FCBean;

/**
 * 
 *新增数据面板,和ParaPanel一样,这里复制代码ParaPanel的代码修改,
 *后面可以采用继承优化代码
 */
public class AddPanel extends DataPanel{
	private final static Log log = LogFactory.getLog(ParaPanel.class);

	private static final long serialVersionUID = 1L;
	
	private static AddPanel instance = new AddPanel();
	
	private AddPanel(){
		super();
		JPanel btnPanel = new JPanel();
		JButton selectBtn = new JButton("确定添加...");
		JButton resetBtn = new JButton("重新填写...");
		btnPanel.add(selectBtn);
		btnPanel.add(resetBtn);
		tc.fill = GridBagConstraints.CENTER;
		tc.gridy = 2;
		tc.gridx = 1;
		add(btnPanel, tc);
		
		selectBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//执行查询,在主界面查询结果中显示查询结果
				log.info("action add data......");
				log.info("check repeat id......");
				if(AppController.getInstance().getInsertFCBean().isNull()){
					AppWinUtils.showWarnMsg("请填写数据,不能插入空数据!");
					return ;
				}
				//id不能重复,数据库里面建立了唯一索引
				FCBean bean = AppController.getInstance().checkInsertAble();
				if(bean != null){
//					JOptionPane.showMessageDialog(ToolUI.getIntance(), "数据[序号 :"+bean.id+" ]重复,不能添加重复的序号,请修改...!", "警告", JOptionPane.WARNING_MESSAGE);
					AppWinUtils.showWarnMsg("数据[序号 :"+bean.id+" ]重复,不能添加重复的序号,请修改...!");
					return;
				}
				
				
				boolean success = AppController.getInstance().insert();
				//
				if(!success){
					AppWinUtils.showWarnMsg("添加数据失败");
				}else{
					AppWinUtils.showNormalMsg("添加数据成功");
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
