package com.sanli.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sanli.logic.AssetManager;
import com.sanli.model.FCBean;

/**批量编辑*/
public class BatchPanel extends JPanel {
	private static final Log log = LogFactory.getLog(BatchPanel.class);
	private static final long serialVersionUID = 1L;

	private static BatchPanel instance = new BatchPanel();
//	public ITable table;
	public BatchEditPanel tablePanel = new BatchEditPanel();
	//用于保存JTable总的List
	public List<FCBean> beanList = new ArrayList<FCBean>();

	private BatchPanel() {
		setLayout(new BorderLayout());
		tablePanel.setBorder(new TitledBorder("数据预览"));

		JPanel inAndOut = new JPanel();
		JButton inBtn = new JButton("批量导入数据");
		JButton outBtn = new JButton("批量导出数据");
		inAndOut.add(inBtn);
		inAndOut.add(outBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(tablePanel);

		add(inAndOut, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		
		inBtn.addActionListener(new BatchImportExcelAction());
		outBtn.addActionListener(new BatchExportExcelAction());
	}

	public static BatchPanel getInstance() {
		return instance;
	}

	public void showInTable(List<FCBean> list){
		tablePanel.showInTable(list);
	}
	
	public void updateBean(FCBean bean, int row){
		beanList.remove(row);
		beanList.add(bean);
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
					beanList = AssetManager.getInstance().importExcel(filePath);
					if(beanList.size() <= 0){
						AppWinUtils.showWarnMsg("Excel文件中可能没有数据 !");
					}else{
//						AppWinUtils.showNormalMsg("导出数据成功,可直接复制到Excel中查看,文件路径[" + filePath +"]");
//						AppWinUtils.showNormalMsg("导入Excel数据成功,文件路径[" + filePath +"]");
						BatchPanel.getInstance().showInTable(beanList);
					}
				} catch(Exception e1) {
					log.error("导出数据错误 , " + e1.getMessage());
				}finally{
//					ExportDialog.getInstance().setVisible(false);
				}

			}
		}
	}
	
	/**导出数据*/
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
					
					 boolean success = AssetManager.getInstance().exportExcel(filePath, tablePanel.beanList);
					if(!success){
						AppWinUtils.showWarnMsg("导出数据失败");
					}else{
//						AppWinUtils.showNormalMsg("导出数据成功,可直接复制到Excel中查看,文件路径[" + filePath +"]");
						AppWinUtils.showNormalMsg("导出Excel数据成功,文件路径[" + filePath +"]");
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
