package com.sanli.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sanli.logic.AppController;
import com.sanli.logic.AssetManager;

public class ExportDialog extends JDialog{
	private final static Log log = LogFactory.getLog(ExportDialog.class);

	private static final long serialVersionUID = 1L;
	
	private static ExportDialog instance = new ExportDialog();
	
	public static ExportDialog getInstance(){
		return instance;
	}
	
	private ExportDialog(){
		JPanel panel = new JPanel();
		
		JButton txtBtn = new JButton("导出为文本文件");
		JButton excelBtn = new JButton("导出为Excel(数据量比较大不建议使用)");
		
		panel.add(txtBtn);
		panel.add(excelBtn);
		
		add(panel);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 300, 100);
		setLocationRelativeTo(null);
		setTitle("导出数据");
//		setModal(true);
		txtBtn.addActionListener(new ExportTxtAction());
		excelBtn.addActionListener(new ExportExcelAction());
		
	}
	
	public void showExport(){
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	class ExportExcelAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			if(AppController.getInstance().getTmpList().size() == 0){
				AppWinUtils.showWarnMsg("没有查询到数据,没必要导出");
				return;
			}
			
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
			fileChooser.setDialogTitle("导出数据");
			// fileChooser.setApproveButtonText("保存");
			// editor.getStyledDocument().getDefaultRootElement();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			fileChooser.setFileFilter(new FileFilter() {
				@Override
				public boolean accept(File f) {
					return f.getName().toLowerCase().endsWith(".txt") || f.getName().toLowerCase().endsWith(".xm") || f.isDirectory();
				}

				@Override
				public String getDescription() {
					return "输入文件名";
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
					boolean success = false;
//					success = AssetManager.getInstance().exportTxt(filePath, AppController.getInstance().getTmpList());
					success = AssetManager.getInstance().exportExcel(filePath, AppController.getInstance().getTmpList());
					if(!success){
						AppWinUtils.showWarnMsg("导出数据失败");
					}else{
//						AppWinUtils.showNormalMsg("导出数据成功,可直接复制到Excel中查看,文件路径[" + filePath +"]");
						AppWinUtils.showNormalMsg("导出Excel数据成功,文件路径[" + filePath +"]");
					}
				} catch(Exception e1) {
					log.error("导出数据错误 , " + e1.getMessage());
				}finally{
					ExportDialog.getInstance().setVisible(false);
				}

			}
		}
	}
	
	class ExportTxtAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			if(AppController.getInstance().getTmpList().size() == 0){
				AppWinUtils.showWarnMsg("没有查询到数据,没必要导出");
				return;
			}
			
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
			fileChooser.setDialogTitle("导出数据");
			// fileChooser.setApproveButtonText("保存");
			// editor.getStyledDocument().getDefaultRootElement();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			fileChooser.setFileFilter(new FileFilter() {
				@Override
				public boolean accept(File f) {
					return f.getName().toLowerCase().endsWith(".txt") || f.getName().toLowerCase().endsWith(".xm") || f.isDirectory();
				}

				@Override
				public String getDescription() {
					return "输入文件名";
				}
			});
			int returnVal = fileChooser.showSaveDialog(getParent());
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				String filePath = selectedFile.getPath();
				if(!filePath.toLowerCase().endsWith(".txt")) {
					// 处理文件名为
					filePath = filePath + ".txt";
				}

				log.info("导出数据 , filePath = " + filePath);
				
				try {
					boolean success = false;
					success = AssetManager.getInstance().exportTxt(filePath, AppController.getInstance().getTmpList());
//					success = AssetManager.getInstance().exportExcel(filePath, AppController.getInstance().getTmpList());
					if(!success){
						AppWinUtils.showWarnMsg("导出数据失败");
					}else{
						AppWinUtils.showNormalMsg("导出数据成功,可直接复制到Excel中查看,文件路径[" + filePath +"]");
//						AppWinUtils.showNormalMsg("导出Excel数据成功,文件路径[" + filePath +"]");
					}
				} catch(Exception e1) {
					log.error("导出数据错误 , " + e1.getMessage());
				}finally{
					ExportDialog.getInstance().setVisible(false);
				}

			}
		}
	}

}
