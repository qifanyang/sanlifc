package com.sanli.model;

import java.lang.reflect.Field;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ������ʾ��ѯ���ݺ͵�������,�ṩһ�������˵�,��ѯ������������õ�
 * 
 */
public class ITable<T> extends JTable {
	private final static Log log = LogFactory.getLog(ITable.class);
	private static final long serialVersionUID = 1L;
	
	private ITableModel model;
	
	public ITable(){
	}
	
	public ITable(List<T> data, List<String> colNames) {
		this.model = new ITableModel(data, colNames);
		setModel(model);
	}

	@SuppressWarnings("unchecked")
	public void refresh(List<T> data) {
		this.model = new ITableModel(data, ((ITableModel)getModel()).colNames);
		setModel(model);
		this.updateUI();
	}
	
	public void refresh(List<T> data, List<String> colNames) {
		this.model = new ITableModel(data, colNames);
		setModel(model);
		this.validate();
		this.updateUI();
	}
	
	public void refresh(){
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
			return 50;// T��ͬ����,���Ȳ�ͬ
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			T t = (T) data.get(rowIndex);
			Field[] fields = t.getClass().getFields();
			int cols = 0;
			for (Field f : fields) {
				if (columnIndex == cols) {
					try {
						return f.get(t);
					} catch (Exception e) {
						log.error("�����쳣 ", e);
					}
				}
				cols++;
			}
			return null;
		}

	}

}
