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

import com.sanli.model.FCBean;
import com.sanli.model.ITable;
import com.sanli.util.Utils;

/**
 * ������ʾ�ͱ༭JTable�����,��ʾ����ʱ�������һ����Ա༭��ɾ�� ������ʾ��Ҫʹ��,��������༭Ҳ��Ҫʹ��
 * 
 * @author XF
 */
public abstract class TablePanel extends JPanel {
	private final static Log log = LogFactory.getLog(TablePanel.class);
	private static final long serialVersionUID = 1L;

	public ITable<FCBean> table;
	private final JPopupMenu tablePopupMenu;
	private final JMenuItem updateMenuItem;
	private final JMenuItem deleteMenuItem;
	
	// ���ڱ���JTable�ܵ�List
	public List<FCBean> beanList = new ArrayList<FCBean>();
	
	public TablePanel(){
		this.setLayout(new BorderLayout());
		
		table = new ITable<FCBean>();
		// ���������˵�
		tablePopupMenu = new JPopupMenu();
		updateMenuItem = new JMenuItem("�޸ĺ�ͬ");
		deleteMenuItem = new JMenuItem("ɾ����ͬ");

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
						doEdit(beanList.get(row).id);
//						EditDialog.getInstance().showEditDialog(beanList.get(row).id);
					}else{
						AppWinUtils.showWarnMsg("ûѡ��,�뵥��ѡ��һ��");
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
					int result = JOptionPane.showConfirmDialog(ToolUI.getIntance(), "ȷ��ɾ������,�����Իָ�Ŷ", "����", JOptionPane.YES_NO_OPTION);
					if(result == 0){
//						ShowPanel.getInstance().showSelectResult(2);
						doDelete(beanList.get(row).id);
					}else{
						return;
					}
				}else{
					AppWinUtils.showWarnMsg("ûѡ��,�뵥��ѡ��һ��");
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
	
	/**���ص����˵�*/
	public void setPopMenu(boolean visible){
		tablePopupMenu.setVisible(visible);
		updateUI();
	}
	
	public void showInTable(List<FCBean> list){
		if (list == null || list.size() <= 0) {
			log.info("û������");
			return;
		}

		this.beanList = list;
		Vector<Vector<String>> rr = new Vector<Vector<String>>();
		try {
			for(FCBean bean : beanList) {
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
		

		table.refresh(list, ToolUI.getIntance().getHeader());
		this.removeAll();
		table.getTableHeader().setVisible(true);
		this.add(table.getTableHeader(), BorderLayout.PAGE_START);
		this.add(table, BorderLayout.CENTER);
		this.updateUI();
		
	}

	public abstract void doEdit(int id);
	public abstract void doDelete(int id);
	
	
}
