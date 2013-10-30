package com.sanli.swing;

import javax.swing.JOptionPane;

/**
 * 窗口小工具
 * @author XF
 */
public class AppWinUtils{

	private static String warn = "警告";
	private static String normal = "信息";
	
	public static void showWarnMsg(String info){
		JOptionPane.showMessageDialog(ToolUI.getIntance(), info, warn, JOptionPane.WARNING_MESSAGE);
	}
	
	public static void showNormalMsg(String info){
		JOptionPane.showMessageDialog(ToolUI.getIntance(), info, normal, JOptionPane.INFORMATION_MESSAGE);
	}
}
