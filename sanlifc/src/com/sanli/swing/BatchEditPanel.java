package com.sanli.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sanli.logic.AppController;
import com.sanli.logic.AssetManager;
import com.sanli.model.FCBean;
import com.sanli.util.LanguageLoader;
import com.sanli.util.Utils;

public class BatchEditPanel extends JPanel {
	private static final Log log = LogFactory.getLog(BatchEditPanel.class);
	private static final long serialVersionUID = 1L;

	private static BatchEditPanel instance = new BatchEditPanel();
	public JTable table;
	Vector<String> header = new Vector<String>();
	JPanel batchShowPanel = new JPanel();

	private BatchEditPanel() {
		setLayout(new BorderLayout());
		batchShowPanel.setBorder(new TitledBorder("����Ԥ��"));

		JPanel inAndOut = new JPanel();
		JButton inBtn = new JButton("������������");
		JButton outBtn = new JButton("������������");
		inAndOut.add(inBtn);
		inAndOut.add(outBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(batchShowPanel);

		add(inAndOut, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		
		inBtn.addActionListener(new ImportExcelAction());
	}

	public static BatchEditPanel getInstance() {
		return instance;
	}

	public void refreshTable(List<FCBean> list) {
		if (list == null || list.size() <= 0) {
			return;
		}
		Vector<Vector<String>> rr = new Vector<Vector<String>>();
		try {
			for (FCBean bean : list) {
				Field[] fields = bean.getClass().getFields();
				Vector<String> r = new Vector<String>();
				for (Field f : fields) {
					if (!f.getName().equalsIgnoreCase("uuid")) {
						addHeaderName(f.getName());
						Class<?> type = f.getType();
						if (type == int.class) {
							r.add(String.valueOf(f.getInt(bean) <= 0 ? "" : f
									.getInt(bean)));
						} else if (type == long.class) {
							r.add(Utils.millisecondToDate(f.getLong(bean)));
						} else if (type == float.class) {
							r.add(String.valueOf(f.getFloat(bean) <= 0 ? "" : f
									.getFloat(bean)));
						} else if (type == String.class) {
							r.add(String.valueOf(f.get(bean) == null ? "" : f
									.get(bean)));
						}
					}
				}
				rr.add(r);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		// Vector<String> header = new Vector<String>();
		// for(int i = 0; i < 50; i++) {
		// header.add(String.valueOf(i));
		// }

		table = new JTable(rr, header);
		batchShowPanel.removeAll();
		JScrollPane scrollPane = new JScrollPane();
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		table.getTableHeader().setVisible(true);
		panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
		panel.add(table, BorderLayout.CENTER);
		scrollPane.setViewportView(panel);
		batchShowPanel.add(scrollPane);
		instance.updateUI();
	}

	private void addHeaderName(String name) {
		if (header.size() < 50) {
			header.add(LanguageLoader.getInstance().getUIName(name));
		}
	}
	
	class ImportExcelAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
			fileChooser.setDialogTitle("��������");
			// fileChooser.setApproveButtonText("����");
			// editor.getStyledDocument().getDefaultRootElement();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			fileChooser.setFileFilter(new FileFilter() {
				@Override
				public boolean accept(File f) {
					return f.getName().toLowerCase().endsWith(".xls") || f.getName().toLowerCase().endsWith(".xm") || f.isDirectory();
				}

				@Override
				public String getDescription() {
					return "ѡ��Ҫ�����Excel�ļ�";
				}
			});
			int returnVal = fileChooser.showSaveDialog(getParent());
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				String filePath = selectedFile.getPath();
				if(!filePath.toLowerCase().endsWith(".xls")) {
					// �����ļ���Ϊ
					filePath = filePath + ".xls";
				}

				log.info("�������� , filePath = " + filePath);
				
				try {
//					 success = false;
					 List<FCBean> list = AssetManager.getInstance().importExcel(filePath);
					if(list.size() <= 0){
						AppWinUtils.showWarnMsg("Excel�ļ��п���û������ !");
					}else{
//						AppWinUtils.showNormalMsg("�������ݳɹ�,��ֱ�Ӹ��Ƶ�Excel�в鿴,�ļ�·��[" + filePath +"]");
						AppWinUtils.showNormalMsg("����Excel���ݳɹ�,�ļ�·��[" + filePath +"]");
						BatchEditPanel.getInstance().refreshTable(list);
					}
				} catch(Exception e1) {
					log.error("�������ݴ��� , " + e1.getMessage());
				}finally{
//					ExportDialog.getInstance().setVisible(false);
				}

			}
		}
	}
}
