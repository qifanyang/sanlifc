package com.sanli.model;

import java.lang.reflect.Field;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sanli.util.Utils;

/**
 * 用于显示查询数据和导入数据,提供一个弹出菜单,查询和批量导入可用到
 * 
 */
public class ITable<T> extends JTable {
	private final static Log log = LogFactory.getLog(ITable.class);
	private static final long serialVersionUID = 1L;

	private ITableModel model;

	public ITable() {
	}

	public ITable(List<T> data, List<String> colNames) {
		this.model = new ITableModel(data, colNames);
		setModel(model);
	}

	@SuppressWarnings("unchecked")
	public void refresh(List<T> data) {
		this.model = new ITableModel(data, ((ITableModel) getModel()).colNames);
		setModel(model);
		this.updateUI();
	}

	public void refresh(List<T> data, List<String> colNames) {
		this.model = new ITableModel(data, colNames);
		setModel(model);
		this.validate();
		this.updateUI();
	}

	public void refresh() {
		this.validate();
		this.updateUI();
	}

	public class ITableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;
		private List<T> data;
		List<String> colNames;

		public ITableModel(List<T> data, List<String> colNames) {
			this.data = data;
			this.colNames = colNames;
		}

		@Override
		public String getColumnName(int column) {
			return colNames.get(column);
		}

		@Override
		public int getRowCount() {
			return data.size();
		}

		@Override
		public int getColumnCount() {
			return 50;// T不同类型,长度不同
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			T t = (T) data.get(rowIndex);
			Field[] fields = t.getClass().getFields();
			int cols = 0;
			for (Field f : fields) {
				if (columnIndex == cols) {
					try {
						if (!f.getName().equalsIgnoreCase("uuid")) {
							Class<?> type = f.getType();
							if (type == int.class) {
								return (String.valueOf(f.getInt(t) <= 0 ? "": f.getInt(t)));
							} else if (type == long.class) {
								return (Utils.millisecondToDate(f.getLong(t)));
							} else if (type == float.class) {
								return (String.valueOf(f.getFloat(t) <= 0 ? "": f.getFloat(t)));
							} else if (type == String.class) {
								return (String.valueOf(f.get(t) == null ? "": f.get(t)));
							}
						} else {
							return f.get(t).toString();
						}
					} catch (Exception e) {
						log.error("反射异常 ", e);
					}
				}
				cols++;
			}
			return null;
		}

	}

}
