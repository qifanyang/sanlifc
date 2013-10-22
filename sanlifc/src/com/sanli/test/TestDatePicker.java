package com.sanli.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.JDatePanel;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class TestDatePicker extends JDialog{

	private static final long serialVersionUID = 1L;
	 /**
     * @param jframe  �ⲿ��JFrame����
     * @param model    �Ƿ�ģʽ���ڣ����ô��ڶ�ռ���в���
     * @param jtextfield   �ⲿJFrame�����һ��JTextField����Ҫ����������ı���
     * @param screen_x    �ⲿ��JTextField�������һ��MouseListener�¼�������e.getXOnScreen() �� e.getYOnScreen(); �������������λ�õ��������ڿ�
     * @param screen_y    ��MouseEvent��e.getXOnScreen() �� e.getYOnScreen()
     */
    public TestDatePicker(JFrame jframe, boolean model,final JTextField jtextfield, int screen_x, int screen_y)
    {
        final JDatePanel jp = JDateComponentFactory.createJDatePanel(new UtilDateModel(new Date()));
        jp.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                try{
                jtextfield.setText(new SimpleDateFormat("yyyy-MM-dd").format(jp.getModel().getValue()));
                //���ѡ�����ں�������JDialog����ôjp.addActionListener��newһ��ActionListener��ʵ���࣬��this���󴫵ݽ�ȥ��Ȼ�����dispose()����
               
                }catch(Exception ex){
                    //�����ڿؼ����Clear��ʱ�����쳣����Ϊû��ѡ�����ڣ����Ҫ�������쳣����ôֱ�ӵ���Դ���룬��Դ������Ķ���
                    //����ֱ�Ӽ򻯲��������Clear�����쳣��ֱ�ӽ�jtextfield��ֵΪ""
                    jtextfield.setText("");
                }
            }
        });
        JPanel jpanel =(JPanel)jp;
        this.add(jpanel);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setBounds(screen_x, screen_y, 300, 320);
        this.setVisible(true);
    }
    public static void main(String[] args){
        new TestDatePicker(null,true,new JTextField(),300,400);
    }

}
