package com.sanli.logic;

import javax.swing.JOptionPane;

import com.sanli.swing.ToolUI;

public class AppWinUtils{

	private static String warn = "����";
	private static String normal = "��Ϣ";
	
	public static void showWarnMsg(String info){
		JOptionPane.showMessageDialog(ToolUI.getIntance(), info, warn, JOptionPane.WARNING_MESSAGE);
	}
	public static void showNormalMsg(String info){
		JOptionPane.showMessageDialog(ToolUI.getIntance(), info, normal, JOptionPane.INFORMATION_MESSAGE);
	}
}
