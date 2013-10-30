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

/**�����༭*/
public class BatchPanel extends JPanel {
	private static final Log log = LogFactory.getLog(BatchPanel.class);
	private static final long serialVersionUID = 1L;

	private static BatchPanel instance = new BatchPanel();
//	public ITable table;
	public BatchEditPanel tablePanel = new BatchEditPanel();
	//���ڱ���JTable�ܵ�List
	public List<FCBean> beanList = new ArrayList<FCBean>();

	private BatchPanel() {
		setLayout(new BorderLayout());
		tablePanel.setBorder(new TitledBorder("����Ԥ��"));

		JPanel inAndOut = new JPanel();
		JButton inBtn = new JButton("������������");
		JButton outBtn = new JButton("������������");
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
					beanList = AssetManager.getInstance().importExcel(filePath);
					if(beanList.size() <= 0){
						AppWinUtils.showWarnMsg("Excel�ļ��п���û������ !");
					}else{
//						AppWinUtils.showNormalMsg("�������ݳɹ�,��ֱ�Ӹ��Ƶ�Excel�в鿴,�ļ�·��[" + filePath +"]");
//						AppWinUtils.showNormalMsg("����Excel���ݳɹ�,�ļ�·��[" + filePath +"]");
						BatchPanel.getInstance().showInTable(beanList);
					}
				} catch(Exception e1) {
					log.error("�������ݴ��� , " + e1.getMessage());
				}finally{
//					ExportDialog.getInstance().setVisible(false);
				}

			}
		}
	}
	
	/**��������*/
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
					
					 boolean success = AssetManager.getInstance().exportExcel(filePath, tablePanel.beanList);
					if(!success){
						AppWinUtils.showWarnMsg("��������ʧ��");
					}else{
//						AppWinUtils.showNormalMsg("�������ݳɹ�,��ֱ�Ӹ��Ƶ�Excel�в鿴,�ļ�·��[" + filePath +"]");
						AppWinUtils.showNormalMsg("����Excel���ݳɹ�,�ļ�·��[" + filePath +"]");
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
