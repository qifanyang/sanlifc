package com.sanli.swing;

import java.awt.BorderLayout;
import java.awt.Component;
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
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sanli.logic.AppController;
import com.sanli.model.FCBean;
import com.sanli.util.LanguageLoader;
import com.sanli.util.Utils;

/**
 * ��ʾ��壬������ʾ��ѯ���
 * 
 * @author XF 2013-10-21 ����10:19:47
 */
public class ShowPanel extends JPanel{
	private final static Log log = LogFactory.getLog(ShowPanel.class);
	private static final long serialVersionUID = 1L;
	private static ShowPanel instance = new ShowPanel();
	private JTable table;
	private final JPopupMenu tablePopupMenu;
	private final JMenuItem updateMenuItem;
	private final JMenuItem deleteMenuItem;
	
	Vector<String> header = new Vector<String>();

	private ShowPanel() {
		
		
//TODO tableʹ��model����������,�����޸�
		this.setLayout(new BorderLayout());
		
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
						FCBean bean = new FCBean();
						bean.id = Integer.parseInt(String.valueOf(table.getValueAt(row, 0)));
						EditDialog.getInstance().showEditDialog(bean);
//						System.out.println("idididididi = " + bean.id);
						return;
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
//					System.out.println(result);
					if(result == 0){
						AppController.getInstance().deleteOne(AppController.getInstance().getTmpList().get(row).id);
						AppWinUtils.showNormalMsg("ɾ���ɹ�!");
						ShowPanel.getInstance().showSelectResult(2);
					}else{
						return;
					}
				}else{
					AppWinUtils.showWarnMsg("ûѡ��,�뵥��ѡ��һ��");
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
	 * ����JTable��ʾ
	 * @type 1:����     2:����
	 * @throws IllegalAccessException
	 * @throws Exception
	 */
	public void showSelectResult(int type1) {
		log.error("show result in JTable....");
		List<FCBean> list = null;
		if(type1 == 1){
			list = AppController.getInstance().select();
		}else if(type1 == 2){
			list = AppController.getInstance().selectAll();
		}
		if(list == null) {
			AppWinUtils.showWarnMsg("��������, ����������쳣, !");
			return;
		}
		if(list.size() == 0) {
			AppWinUtils.showNormalMsg("��������");
			return;
		}

		Vector<Vector<String>> rr = new Vector<Vector<String>>();
		try {
			for(FCBean bean : list) {
				Field[] fields = bean.getClass().getFields();
				Vector<String> r = new Vector<String>();
				for(Field f : fields) {
					if(!f.getName().equalsIgnoreCase("uuid")) {
						addHeaderName(f.getName());
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

//		Vector<String> header = new Vector<String>();
//		for(int i = 0; i < 50; i++) {
//			header.add(String.valueOf(i));
//		}

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
		
//		int columnCount = table.getColumnCount();
//		for(int i =0; i < columnCount; i++){
//			adjustColumnPreferredWidths(table, i);
//		}

	}
	
	private void addHeaderName(String name){
		if(header.size() < 50){
			header.add(LanguageLoader.getInstance().getUIName(name));
		}
	}
	
	 public static void adjustColumnPreferredWidths(JTable table, int col) {
	        // strategy - get max width for cells in column and
	        // make that the preferred width
		TableColumnModel columnModel = table.getColumnModel();
		TableColumnModel columnModel2 = table.getTableHeader().getColumnModel();
		int maxwidth = 0;
		for(int row = 0; row < table.getRowCount(); row++) {
			TableCellRenderer rend = table.getCellRenderer(row, col);
			Object value = table.getValueAt(row, col);
			Component comp = rend.getTableCellRendererComponent(table, value, false, false, row, col);
			maxwidth = Math.max(comp.getPreferredSize().width, maxwidth);
		} // for row
		TableColumn column = columnModel.getColumn(col);
		column.setPreferredWidth(maxwidth + 3);
	}

}
