package com.sanli.swing;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JTextField;

import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.JDatePanel;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 * ����ʱ��ؼ�����
 *
 * @author XF
 * 2013-10-22 ����9:36:15
 */
public class DatePickerFactory {
	
	private  static JDatePanel datePanel;
	
	private static JDialog dialog;
	
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
	                //���ѡ�����ں�������JDialog����ôjp.addActionListener��newһ��ActionListener��ʵ���࣬��this���󴫵ݽ�ȥ��Ȼ�����dispose()����
	               
	                }catch(Exception ex){
	                    //�����ڿؼ����Clear��ʱ�����쳣����Ϊû��ѡ�����ڣ����Ҫ�������쳣����ôֱ�ӵ���Դ���룬��Դ������Ķ���
	                    //����ֱ�Ӽ򻯲��������Clear�����쳣��ֱ�ӽ�jtextfield��ֵΪ""
	                	DatePickerFactory.jtf.setText("");
	                }
	            }
	        });
			
			dialog = new JDialog(ToolUI.getIntance());
			dialog.setTitle("ʱ��");
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
