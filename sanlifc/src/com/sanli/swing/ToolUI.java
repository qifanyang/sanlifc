package com.sanli.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;

import com.sanli.logic.Controller;
import com.sanli.model.FCBean;


/**
 * ����ϵͳ�༭��
 * 
 * @author qifan.yang
 * 
 */
public class ToolUI extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1100;
	public static final int HEIGHT = 800;

	public static BufferedImage icon;
	public static ToolUI instancEditor;
	public static JPanel dataSelectPanel;
	public static JPanel dataAddPanel;


	public ToolUI() {

	}

	private void initUI() {
		// try {
		// icon =
		// ImageIO.read(getClass().getClassLoader().getResourceAsStream("com/help/seasky32.png"));
		// icon =
		// ImageIO.read(getClass().getClassLoader().getResourceAsStream("com/help/res/long.png"));
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// icon = this.getToolkit().getImage("seasky16.png");
		// setIconImage(icon);
		setTitle("��ͬ�������ݹ�����");
		setLayout(new BorderLayout());

		// create editor panel
		// EditorPanel editorPanel = new EditorPanel();

		 setJMenuBar(new TopMenu());
		// JToolBar toolBar = new CustomToolBar(editorPanel);

		JTabbedPane tab = new JTabbedPane();
		tab.add("��ѯ����",new JScrollPane(new DataSelectPanel()));
		tab.add("�������",new DataAddPanel());
		
		getContentPane().add(tab, BorderLayout.CENTER);
		// add(editorPanel, BorderLayout.CENTER);
//		setSize(new Dimension(WIDTH, HEIGHT));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		instancEditor = this;
		setResizable(false);

	}

	public static ToolUI getIntance() {
		return instancEditor;
	}
	
	

	public static void main(String[] dd) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		ToolUI ui = new ToolUI();
		ui.initUI();
	}
	
	public class DataSelectPanel extends JPanel{

		private static final long serialVersionUID = 1L;
		
		public DataSelectPanel(){
			JPanel pPanel = new JPanel();//�����������
			pPanel.setPreferredSize(new Dimension(getPreferredSize().width, 380));
			TitledBorder paraBorder = new TitledBorder("����");
			pPanel.setBorder(paraBorder);
			ParaPanel paraPanel = ParaPanel.getInstance();
			pPanel.add(paraPanel);
			
			
			JPanel sPanel = new JPanel();//��ѯ��������
			TitledBorder showBorder = new TitledBorder("��ѯ���");
			sPanel.setBorder(showBorder);
			sPanel.setLayout(new BorderLayout());
			ShowPanel showPanel = ShowPanel.getInstance();
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setViewportView(showPanel);
			sPanel.add(scrollPane, BorderLayout.CENTER);
			
			setLayout(new BorderLayout());
			
			add(pPanel, BorderLayout.NORTH);
			
			add(sPanel, BorderLayout.CENTER);
//			dataSelectPanel = this;
			
			//TODO ��С�������ParaPanel����ʾ���ֹ���ScrollPanel��,���ù���
			
		}
		
		
	}
	public class DataAddPanel extends JPanel{
		
		private static final long serialVersionUID = 1L;
		
		public DataAddPanel(){
			
			
			dataAddPanel = this;
		}
		
	}
}
