package com.sanli.swing;

import java.util.List;

import javax.swing.JPanel;

import com.sanli.logic.Controller;
import com.sanli.model.FCBean;

/**
 * 显示面板，用于显示查询结果
 *
 * @author XF
 * 2013-10-21 下午10:19:47
 */
public class ShowPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private static ShowPanel instance = new ShowPanel();
	
	private ShowPanel(){}
	
	public static ShowPanel getInstance(){
		return instance;
	}
	
	/**
	 * 采用JTable显示
	 */
	public void showSelectResult(){
		List<FCBean> list = Controller.getInstance().select();
		
	}

}
