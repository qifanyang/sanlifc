package com.sanli.swing;

import java.awt.BorderLayout;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sanli.logic.AppController;
import com.sanli.logic.Utils;
import com.sanli.model.FCBean;

/**
 * 显示面板，用于显示查询结果
 *
 * @author XF
 * 2013-10-21 下午10:19:47
 */
public class ShowPanel extends JPanel{
	private final static Log log = LogFactory.getLog(ShowPanel.class);
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
		log.debug("show result in JTable....");
		List<FCBean> list = AppController.getInstance().select();
		if(list == null){
			Utils.showMsg("查无数据, 程序出现了异常, !", "警告");
			return;
		}
		if(list.size() == 0){
			Utils.showMsg("查无数据", "信息");
			return;
		}
		
		Vector<Vector<String>> rr = new Vector<Vector<String>>();
		try{
			for(FCBean bean : list){
				Field[] fields = bean.getClass().getFields();
				Vector<String> r = new Vector<String>();
				for(Field f : fields){
					if(!f.getName().equalsIgnoreCase("uuid")){
						Class<?> type = f.getType();
						if(type == int.class){
							r.add(String.valueOf(f.getInt(bean) <= 0 ? "" : f.getInt(bean) ));
						}else if(type == long.class){
							r.add(Utils.millisecondToDate(f.getLong(bean)));
						}else if(type == float.class){
							r.add(String.valueOf(f.getFloat(bean) <= 0 ? "" : f.getFloat(bean)));
						}else if(type == String.class){
							r.add(String.valueOf(f.get(bean) == null ? "" : f.get(bean)));
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
		instance.removeAll();
		instance.updateUI();
		table.getTableHeader().setVisible(true);
		instance.add(table.getTableHeader(), BorderLayout.PAGE_START);
		instance.add(table, BorderLayout.CENTER);
//		instance.add(scrollPane);
		instance.updateUI();
	}

}
