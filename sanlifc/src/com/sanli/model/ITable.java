package com.sanli.model;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * ������ʾ��ѯ���ݺ͵�������,�ṩһ�������˵�,��ѯ������������õ�
 * 
 */
public class ITable extends JTable{
	private final static Log log = LogFactory.getLog(ITable.class);
	private static final long serialVersionUID = 1L;

	private ITableModel model;

	public ITable() {
	}

	public ITable(List<List<String>> data, List<String> colNames) {
		this.model = new ITableModel(data, colNames);
		setModel(model);
	}

	@SuppressWarnings("unchecked")
	public void refresh(List<List<String>> data) {
		this.model = new ITableModel(data, ((ITableModel) getModel()).colNames);
		setModel(model);
		this.updateUI();
	}

	public void refresh(List<List<String>> data, List<String> colNames) {
		this.model = new ITableModel(data, colNames);
		setModel(model);
		this.validate();
		this.updateUI();
	}

	public void refresh() {
		this.validate();
		this.updateUI();
	}

	public class ITableModel extends AbstractTableModel{

		private static final long serialVersionUID = 1L;
		private List<List<String>> data;
		List<String> colNames;

		public ITableModel(List<List<String>> data, List<String> colNames) {
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
			List<String> rowList = data.get(rowIndex);
			return rowList.get(columnIndex);
		}

	}

}
