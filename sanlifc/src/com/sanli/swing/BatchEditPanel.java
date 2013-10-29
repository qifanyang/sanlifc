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

import com.sanli.logic.AssetManager;
import com.sanli.model.FCBean;
import com.sanli.model.ITable;
import com.sanli.util.LanguageLoader;
import com.sanli.util.Utils;


public class BatchEditPanel extends JPanel {
	private static final Log log = LogFactory.getLog(BatchEditPanel.class);
	private static final long serialVersionUID = 1L;

	private static BatchEditPanel instance = new BatchEditPanel();
	public ITable<FCBean> table;
	public BatchPanel batchShowPanel = new BatchPanel();
	//���ڱ���JTable�ܵ�List
	public List<FCBean> beanList = new ArrayList<FCBean>();

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
		
		inBtn.addActionListener(new BatchImportExcelAction());
	}

	public static BatchEditPanel getInstance() {
		return instance;
	}

	public void showInTable(List<FCBean> list){
		batchShowPanel.showInTable(list);
	}
	
	class BatchImportExcelAction implements ActionListener{

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
						BatchEditPanel.getInstance().showInTable(list);
					}
				} catch(Exception e1) {
					log.error("�������ݴ��� , " + e1.getMessage());
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
					return "���뵽����Excel�ļ���";
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
						BatchEditPanel.getInstance().showInTable(list);
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
