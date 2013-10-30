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

/**
 * ����TablePanel���Table�е�����
 * @author XF
 */
public class ExportDialog extends JDialog{
	private final static Log log = LogFactory.getLog(ExportDialog.class);

	private static final long serialVersionUID = 1L;
	
	private static ExportDialog instance = new ExportDialog();
	
	private TablePanel tp;
	
	public static ExportDialog getInstance(){
		return instance;
	}
	
	private ExportDialog(){
		JPanel panel = new JPanel();
		
		JButton txtBtn = new JButton("����Ϊ�ı��ļ�");
		JButton excelBtn = new JButton("����ΪExcel(�������Ƚϴ󲻽���ʹ��)");
		
		panel.add(txtBtn);
		panel.add(excelBtn);
		
		add(panel);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 300, 100);
		setLocationRelativeTo(null);
		setTitle("��������");
//		setModal(true);
		txtBtn.addActionListener(new ExportTxtAction());
		excelBtn.addActionListener(new ExportExcelAction());
		
	}
	
	public void showExport(TablePanel tp){
		setLocationRelativeTo(null);
		setVisible(true);
		this.tp = tp;
	}
	
	class ExportExcelAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			if(!tp.isHaveData()){
				AppWinUtils.showWarnMsg("�����û������,û��Ҫ����");
				return;
			}
			
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
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
					return "�����ļ���";
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
					boolean success = false;
//					success = AssetManager.getInstance().exportTxt(filePath, AppController.getInstance().getTmpList());
					success = AssetManager.getInstance().exportExcel(filePath, tp.beanList);
					if(!success){
						AppWinUtils.showWarnMsg("��������ʧ��");
					}else{
//						AppWinUtils.showNormalMsg("�������ݳɹ�,��ֱ�Ӹ��Ƶ�Excel�в鿴,�ļ�·��[" + filePath +"]");
						AppWinUtils.showNormalMsg("����Excel���ݳɹ�,�ļ�·��[" + filePath +"]");
					}
				} catch(Exception e1) {
					log.error("�������ݴ��� , " + e1.getMessage());
				}finally{
					ExportDialog.getInstance().setVisible(false);
				}

			}
		}
	}
	
	class ExportTxtAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			if(AppController.getInstance().getTmpList().size() == 0){
				AppWinUtils.showWarnMsg("û�в�ѯ������,û��Ҫ����");
				return;
			}
			
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
			fileChooser.setDialogTitle("��������");
			// fileChooser.setApproveButtonText("����");
			// editor.getStyledDocument().getDefaultRootElement();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			fileChooser.setFileFilter(new FileFilter() {
				@Override
				public boolean accept(File f) {
					return f.getName().toLowerCase().endsWith(".txt") || f.getName().toLowerCase().endsWith(".xm") || f.isDirectory();
				}

				@Override
				public String getDescription() {
					return "�����ļ���";
				}
			});
			int returnVal = fileChooser.showSaveDialog(getParent());
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				String filePath = selectedFile.getPath();
				if(!filePath.toLowerCase().endsWith(".txt")) {
					// �����ļ���Ϊ
					filePath = filePath + ".txt";
				}

				log.info("�������� , filePath = " + filePath);
				
				try {
					boolean success = false;
					success = AssetManager.getInstance().exportTxt(filePath, tp.beanList);
//					success = AssetManager.getInstance().exportExcel(filePath, AppController.getInstance().getTmpList());
					if(!success){
						AppWinUtils.showWarnMsg("��������ʧ��");
					}else{
						AppWinUtils.showNormalMsg("�������ݳɹ�,��ֱ�Ӹ��Ƶ�Excel�в鿴,�ļ�·��[" + filePath +"]");
//						AppWinUtils.showNormalMsg("����Excel���ݳɹ�,�ļ�·��[" + filePath +"]");
					}
				} catch(Exception e1) {
					log.error("�������ݴ��� , " + e1.getMessage());
				}finally{
					ExportDialog.getInstance().setVisible(false);
				}

			}
		}
	}

}
