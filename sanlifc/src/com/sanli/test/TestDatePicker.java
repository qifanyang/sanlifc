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
     * @param jframe  外部的JFrame对象
     * @param model    是否模式窗口，即该窗口独占所有操作
     * @param jtextfield   外部JFrame里面的一个JTextField对象即要填入的日期文本域
     * @param screen_x    外部的JTextField点击触发一个MouseListener事件，传入e.getXOnScreen() 和 e.getYOnScreen(); 即决定随着鼠标位置弹出该日期框
     * @param screen_y    即MouseEvent的e.getXOnScreen() 和 e.getYOnScreen()
     */
    public TestDatePicker(JFrame jframe, boolean model,final JTextField jtextfield, int screen_x, int screen_y)
    {
        final JDatePanel jp = JDateComponentFactory.createJDatePanel(new UtilDateModel(new Date()));
        jp.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                try{
                jtextfield.setText(new SimpleDateFormat("yyyy-MM-dd").format(jp.getModel().getValue()));
                //如果选中日期后，想消除JDialog，那么jp.addActionListener（new一个ActionListener的实现类，将this对象传递进去）然后调用dispose()方法
               
                }catch(Exception ex){
                    //该日期控件点击Clear的时候会出异常，因为没有选中日期，如果要消除该异常，那么直接导入源代码，在源码里面改动。
                    //这里直接简化操作，点击Clear出现异常，直接将jtextfield赋值为""
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
