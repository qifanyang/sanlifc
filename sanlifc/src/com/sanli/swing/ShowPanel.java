package com.sanli.swing;

import java.util.List;

import javax.swing.JPanel;

import com.sanli.logic.Controller;
import com.sanli.model.FCBean;

/**
 * ��ʾ��壬������ʾ��ѯ���
 *
 * @author XF
 * 2013-10-21 ����10:19:47
 */
public class ShowPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private static ShowPanel instance = new ShowPanel();
	
	private ShowPanel(){}
	
	public static ShowPanel getInstance(){
		return instance;
	}
	
	/**
	 * ����JTable��ʾ
	 */
	public void showSelectResult(){
		List<FCBean> list = Controller.getInstance().select();
		
	}

}
