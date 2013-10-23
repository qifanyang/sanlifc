package com.sanli.swing;

import java.awt.BorderLayout;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
	
	private ShowPanel(){
		this.setLayout(new BorderLayout());
	}
	
	public static ShowPanel getInstance(){
		return instance;
	}
	
	/**
	 * 采用JTable显示
	 * @throws IllegalAccessException 
	 * @throws Exception 
	 */
	public void showSelectResult() {
		List<FCBean> list = Controller.getInstance().select();
		Vector<String> r = new Vector<String>();
		Vector<Vector<String>> rr = new Vector<Vector<String>>();
		try{
			for(FCBean bean : list){
				Field[] fields = bean.getClass().getFields();
				for(Field f : fields){
					if(!f.getName().equalsIgnoreCase("uuid")){
						Class<?> type = f.getType();
						if(type == int.class){
							r.add(String.valueOf(f.getInt(bean)));
						}else if(type == long.class){
							r.add(String.valueOf(f.getLong(bean)));
						}else if(type == float.class){
							r.add(String.valueOf(f.getFloat(bean)));
						}else if(type == String.class){
							r.add(String.valueOf(f.get(bean)));
						}
					}
				}
				rr.add(r);
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		Vector<String> header = new Vector<String>();
		for(int i = 0; i < 50 ; i++){
			header.add(String.valueOf(i));
		}
		
		JTable table = new JTable(rr, header);
//		JScrollPane scrollPane = new JScrollPane(table);
		this.removeAll();
//		table.getTableHeader().setVisible(true);
		this.add(table.getTableHeader(), BorderLayout.PAGE_START);
		this.add(table, BorderLayout.CENTER);
		this.updateUI();
	}

}
