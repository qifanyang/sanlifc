package com.sanli.swing;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.JDatePanel;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 * 日期时间控件工厂
 *
 * @author XF
 * 2013-10-22 下午9:36:15
 */
public class DatePickerFactory {
	
	private  static JDatePanel datePanel;
	
	private static JFrame dialog;
	
	private static JTextField jtf;

	public static void showDatePicker(final JTextField jtf , int screen_x, int screen_y){
		DatePickerFactory.jtf = jtf;
		if(dialog == null){
			
			    datePanel = JDateComponentFactory.createJDatePanel(new UtilDateModel(new Date()));
						datePanel.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e)
	            {
	                try{
	                	DatePickerFactory.jtf.setText(new SimpleDateFormat("yyyy-MM-dd").format(datePanel.getModel().getValue()));
	                //如果选中日期后，想消除JDialog，那么jp.addActionListener（new一个ActionListener的实现类，将this对象传递进去）然后调用dispose()方法
	               
	                }catch(Exception ex){
	                    //该日期控件点击Clear的时候会出异常，因为没有选中日期，如果要消除该异常，那么直接导入源代码，在源码里面改动。
	                    //这里直接简化操作，点击Clear出现异常，直接将jtextfield赋值为""
	                	DatePickerFactory.jtf.setText("");
	                }
	            }
	        });
			
			dialog = new JFrame();
			dialog.setTitle("时间");
	//		dialog.setUndecorated(true);
	//		dialog.setModal(true);
			dialog.add((Component) datePanel);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setBounds(0, 0, 300, 320);
			dialog.setLocationRelativeTo(null);
		}
		
		dialog.setVisible(true);
	}

}
