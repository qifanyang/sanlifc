package com.sanli.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sanli.logic.AppController;
import com.sanli.model.FCBean;
import com.sanli.model.ITable;
import com.sanli.util.LanguageLoader;
import com.sanli.util.Utils;

/**
 * 用于显示和编辑JTable的面板,显示数据时在上面右击可以编辑和删除 查找显示需要使用,批量导入编辑也需要使用
 * 
 * @author XF
 */
public abstract class TablePanel extends JPanel {
	private final static Log log = LogFactory.getLog(TablePanel.class);
	private static final long serialVersionUID = 1L;

	public ITable table;
	private final JPopupMenu tablePopupMenu;
	private final JMenuItem updateMenuItem;
	private final JMenuItem deleteMenuItem;
	
	
	// 用于保存JTable总的List
	public List<FCBean> beanList = new ArrayList<FCBean>();
	
	public TablePanel(){
		this.setLayout(new BorderLayout());
		
		table = new ITable();
		// 创建弹出菜单
		tablePopupMenu = new JPopupMenu();
		updateMenuItem = new JMenuItem("修改合同");
		deleteMenuItem = new JMenuItem("删除合同");

		tablePopupMenu.add(updateMenuItem);
		tablePopupMenu.add(new JPopupMenu.Separator());
		tablePopupMenu.add(deleteMenuItem);
		tablePopupMenu.setInvoker(new JTable());
		tablePopupMenu.setBorderPainted(true);
		
		updateMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table != null) {
					tablePopupMenu.setVisible(false);
					int row = table.getSelectedRow();
					System.out.println("row = " + row);
					if(row > -1) {
						doEdit(beanList.get(row).id, row);
//						EditDialog.getInstance().showEditDialog(beanList.get(row).id);
					}else{
						AppWinUtils.showWarnMsg("没选中,请单击选中一行");
					}
				}
			}
		});
		
		deleteMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				tablePopupMenu.setVisible(false);
				int row = table.getSelectedRow();
				System.out.println("row = " + row);
				if(row > -1) {
					int result = JOptionPane.showConfirmDialog(ToolUI.getIntance(), "确定删除数据,不可以恢复哦", "警告", JOptionPane.YES_NO_OPTION);
					if(result == 0){
//						ShowPanel.getInstance().showSelectResult(2);
						doDelete(beanList.get(row).id, row);
					}else{
						return;
					}
				}else{
					AppWinUtils.showWarnMsg("没选中,请单击选中一行");
				}
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if(e.getButton() == MouseEvent.BUTTON3) {
					tablePopupMenu.setLocation(e.getXOnScreen(), e.getYOnScreen());
					updateUI();
					tablePopupMenu.setVisible(true);
				} else {
					tablePopupMenu.setVisible(false);
				}
			}
		});

	}
	
	public boolean isHaveData(){
		return beanList.size() > 0;
	}
	
	/**隐藏弹出菜单*/
	public void setPopMenu(boolean visible){
		tablePopupMenu.setVisible(visible);
		updateUI();
	}
	
	public void refresh(){
		table.refresh();
	}
	
	public void showInTable(List<FCBean> list){
		if (list == null || list.size() <= 0) {
			log.info("没有数据");
			return;
		}

		this.beanList = list;
//		Vector<Vector<String>> rr = new Vector<Vector<String>>();
//		try {
//			for(FCBean bean : beanList) {
//				Field[] fields = bean.getClass().getFields();
//				Vector<String> r = new Vector<String>();
//				for(Field f : fields) {
//					if(!f.getName().equalsIgnoreCase("uuid")) {
//						Class<?> type = f.getType();
//						if(type == int.class) {
//							r.add(String.valueOf(f.getInt(bean) <= 0 ? "" : f.getInt(bean)));
//						} else if(type == long.class) {
//							r.add(Utils.millisecondToDate(f.getLong(bean)));
//						} else if(type == float.class) {
//							r.add(String.valueOf(f.getFloat(bean) <= 0 ? "" : f.getFloat(bean)));
//						} else if(type == String.class) {
//							r.add(String.valueOf(f.get(bean) == null ? "" : f.get(bean)));
//						}
//					}
//				}
//				rr.add(r);
//			}
//		} catch(Exception e) {
//			System.out.println(e);
//		}
//		

		table.refresh(covertListToTable(list), ToolUI.getIntance().getHeader());
		this.removeAll();
		table.getTableHeader().setVisible(true);
		this.add(table.getTableHeader(), BorderLayout.PAGE_START);
		this.add(table, BorderLayout.CENTER);
		this.updateUI();
		
	}
	
	public List<List<String>> covertListToTable(List<FCBean> list){
		List<List<String>> arrayList = new ArrayList<List<String>>(list.size());
		for(int i = 0; i < list.size(); i++){
			ArrayList<String> rowList = new ArrayList<String>();
			FCBean bean = list.get(i);
			Field[] fields = bean.getClass().getFields();
			for(Field f : fields){
				if(f.getName().equals("uuid")){//不现实uuid
					continue;
				}
				try {
					Class<?> type = f.getType();
					if(type == int.class){
						rowList.add(String.valueOf(f.getInt(bean)));
					}else if(type == long.class){
						rowList.add(Utils.millisecondToDate(f.getLong(bean)));
					}else if(type == float.class){
						rowList.add(String.valueOf(f.getFloat(bean)));
//						field.set(this, value == null || value.length() ==0 ? 0 : Float.parseFloat(value));
					}else if(type == String.class){
						Object object = f.get(bean);
						if(object == null){
							rowList.add("");
						}else{
							rowList.add(object.toString());
						}
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			arrayList.add(rowList);
		}
		return arrayList;
	}

	
	
	public abstract void doEdit(int id, int row);
	public abstract void doDelete(int id, int row);
	
	
}
