package com.sanli.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
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
import com.sanli.util.Utils;

/**
 * 显示面板，用于显示查询结果
 * 
 * @author XF 2013-10-21 下午10:19:47
 */
public class ShowPanel extends JPanel{
	private final static Log log = LogFactory.getLog(ShowPanel.class);
	private static final long serialVersionUID = 1L;
	private static ShowPanel instance = new ShowPanel();
	private JTable table;
	private final JPopupMenu tablePopupMenu;
	private final JMenuItem updateMenuItem;
	private final JMenuItem deleteMenuItem;

	private ShowPanel() {
//TODO table使用model来跟新数据,后面修改
		this.setLayout(new BorderLayout());
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
						FCBean bean = new FCBean();
						bean.id = Integer.parseInt(String.valueOf(table.getValueAt(row, 0)));
						EditDialog.getInstance().showEditDialog(bean);
						System.out.println("idididididi = " + bean.id);
						return;
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
//					System.out.println(result);
					if(result == 0){
						AppController.getInstance().deleteOne(AppController.getInstance().getTmpList().get(row).id);
						AppWinUtils.showNormalMsg("删除成功!");
						ShowPanel.getInstance().showSelectResult();
					}else{
						return;
					}
				}else{
					AppWinUtils.showWarnMsg("没选中,请单击选中一行");
				}
			}
		});

	}

	public static ShowPanel getInstance() {
		return instance;
	}

	
	public void setPopMenu(boolean visible){
		tablePopupMenu.setVisible(visible);
		updateUI();
	}
	/**
	 * 采用JTable显示
	 * 
	 * @throws IllegalAccessException
	 * @throws Exception
	 */
	public void showSelectResult() {
		log.debug("show result in JTable....");
		List<FCBean> list = AppController.getInstance().select();
		if(list == null) {
			AppWinUtils.showWarnMsg("查无数据, 程序出现了异常, !");
			return;
		}
		if(list.size() == 0) {
			AppWinUtils.showNormalMsg("查无数据");
			return;
		}

		Vector<Vector<String>> rr = new Vector<Vector<String>>();
		try {
			for(FCBean bean : list) {
				Field[] fields = bean.getClass().getFields();
				Vector<String> r = new Vector<String>();
				for(Field f : fields) {
					if(!f.getName().equalsIgnoreCase("uuid")) {
						Class<?> type = f.getType();
						if(type == int.class) {
							r.add(String.valueOf(f.getInt(bean) <= 0 ? "" : f.getInt(bean)));
						} else if(type == long.class) {
							r.add(Utils.millisecondToDate(f.getLong(bean)));
						} else if(type == float.class) {
							r.add(String.valueOf(f.getFloat(bean) <= 0 ? "" : f.getFloat(bean)));
						} else if(type == String.class) {
							r.add(String.valueOf(f.get(bean) == null ? "" : f.get(bean)));
						}
					}
				}
				rr.add(r);
			}
		} catch(Exception e) {
			System.out.println(e);
		}

		Vector<String> header = new Vector<String>();
		for(int i = 0; i < 50; i++) {
			header.add(String.valueOf(i));
		}

		table = new JTable(rr, header);
		// JScrollPane scrollPane = new JScrollPane(table);
		instance.removeAll();
		table.getTableHeader().setVisible(true);
		instance.add(table.getTableHeader(), BorderLayout.PAGE_START);
		instance.add(table, BorderLayout.CENTER);
		instance.updateUI();
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

}
