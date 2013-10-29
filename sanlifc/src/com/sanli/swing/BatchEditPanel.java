package com.sanli.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
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
	public Vector<String> header = new Vector<String>();
	public JPanel batchShowPanel = new JPanel();
	//用于批量导入数据-->编辑数据-->导出数据
	public List<FCBean> beanList = new ArrayList<FCBean>();

	private BatchEditPanel() {
		setLayout(new BorderLayout());
		batchShowPanel.setBorder(new TitledBorder("数据预览"));

		JPanel inAndOut = new JPanel();
		JButton inBtn = new JButton("批量导入数据");
		JButton outBtn = new JButton("批量导出数据");
		inAndOut.add(inBtn);
		inAndOut.add(outBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(batchShowPanel);

		add(inAndOut, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		
		inBtn.addActionListener(new BatchImportExcelAction());
	}

	public static BatchEditPanel getInstance() {
		return instance;
	}

	public void refreshTable(List<FCBean> list) {
		if (list == null || list.size() <= 0) {
			return;
		}
		this.beanList = list;//
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
							r.add(String.valueOf(f.getInt(bean) <= 0 ? "" : f.getInt(bean)));
						} else if (type == long.class) {
							r.add(Utils.millisecondToDate(f.getLong(bean)));
						} else if (type == float.class) {
							r.add(String.valueOf(f.getFloat(bean) <= 0 ? "" : f.getFloat(bean)));
						} else if (type == String.class) {
							r.add(String.valueOf(f.get(bean) == null ? "" : f.get(bean)));
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
	
	class BatchImportExcelAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
			fileChooser.setDialogTitle("导入数据");
			// fileChooser.setApproveButtonText("保存");
			// editor.getStyledDocument().getDefaultRootElement();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			fileChooser.setFileFilter(new FileFilter() {
				@Override
				public boolean accept(File f) {
					return f.getName().toLowerCase().endsWith(".xls") || f.getName().toLowerCase().endsWith(".xm") || f.isDirectory();
				}

				@Override
				public String getDescription() {
					return "选择要导入的Excel文件";
				}
			});
			int returnVal = fileChooser.showSaveDialog(getParent());
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				String filePath = selectedFile.getPath();
				if(!filePath.toLowerCase().endsWith(".xls")) {
					// 处理文件名为
					filePath = filePath + ".xls";
				}

				log.info("导入数据 , filePath = " + filePath);
				
				try {
//					 success = false;
					 List<FCBean> list = AssetManager.getInstance().importExcel(filePath);
					if(list.size() <= 0){
						AppWinUtils.showWarnMsg("Excel文件中可能没有数据 !");
					}else{
//						AppWinUtils.showNormalMsg("导出数据成功,可直接复制到Excel中查看,文件路径[" + filePath +"]");
						AppWinUtils.showNormalMsg("导入Excel数据成功,文件路径[" + filePath +"]");
						BatchEditPanel.getInstance().refreshTable(list);
					}
				} catch(Exception e1) {
					log.error("导出数据错误 , " + e1.getMessage());
				}finally{
//					ExportDialog.getInstance().setVisible(false);
				}

			}
		}
	}
	
	class BatchExportExcelAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
			fileChooser.setDialogTitle("导出数据");
			// fileChooser.setApproveButtonText("保存");
			// editor.getStyledDocument().getDefaultRootElement();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			fileChooser.setFileFilter(new FileFilter() {
				@Override
				public boolean accept(File f) {
					return f.getName().toLowerCase().endsWith(".xls") || f.getName().toLowerCase().endsWith(".xm") || f.isDirectory();
				}

				@Override
				public String getDescription() {
					return "输入到处的Excel文件名";
				}
			});
			int returnVal = fileChooser.showSaveDialog(getParent());
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				String filePath = selectedFile.getPath();
				if(!filePath.toLowerCase().endsWith(".xls")) {
					// 处理文件名为
					filePath = filePath + ".xls";
				}

				log.info("导出数据 , filePath = " + filePath);
				
				try {
//					 success = false;
					 List<FCBean> list = AssetManager.getInstance().importExcel(filePath);
					if(list.size() <= 0){
						AppWinUtils.showWarnMsg("Excel文件中可能没有数据 !");
					}else{
//						AppWinUtils.showNormalMsg("导出数据成功,可直接复制到Excel中查看,文件路径[" + filePath +"]");
						AppWinUtils.showNormalMsg("导入Excel数据成功,文件路径[" + filePath +"]");
						BatchEditPanel.getInstance().refreshTable(list);
					}
				} catch(Exception e1) {
					log.error("导出数据错误 , " + e1.getMessage());
				}finally{
//					ExportDialog.getInstance().setVisible(false);
				}

			}
		}
	}
	
	
}
