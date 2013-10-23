package com.sanli.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
 * 帮助系统编辑器
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
		setTitle("合同流程数据管理工具");
		setLayout(new BorderLayout());

		// create editor panel
		// EditorPanel editorPanel = new EditorPanel();

		 setJMenuBar(new TopMenu());
		// JToolBar toolBar = new CustomToolBar(editorPanel);

		JTabbedPane tab = new JTabbedPane();
		tab.add("查询数据",new DataSelectPanel());
		tab.add("添加数据",new DataAddPanel());
		
		getContentPane().add(tab, BorderLayout.CENTER);
		// add(editorPanel, BorderLayout.CENTER);
//		setSize(new Dimension(WIDTH, HEIGHT));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
//		setLocationRelativeTo(null);
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
			JPanel pPanel = new JPanel();//参数输入面板
			pPanel.setPreferredSize(new Dimension(500, 380));
			TitledBorder paraBorder = new TitledBorder("参数");
			pPanel.setBorder(paraBorder);
			ParaPanel paraPanel = ParaPanel.getInstance();
			pPanel.add(paraPanel);
			
			
			JPanel sPanel = new JPanel();//查询结果面面板
			TitledBorder showBorder = new TitledBorder("查询结果");
			sPanel.setBorder(showBorder);
			sPanel.setLayout(new BorderLayout());
			ShowPanel showPanel = ShowPanel.getInstance();
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setViewportView(showPanel);
			sPanel.add(scrollPane, BorderLayout.CENTER);
			
			setLayout(new BorderLayout());
//			GridBagConstraints tc = new GridBagConstraints();
//			tc.fill = GridBagConstraints.BOTH;
//			// c.fill = GridBagConstraints.REMAINDER;
//			tc.weightx = 1.0;
//			tc.weighty = 1.0;
//			tc.gridy = 0;
//			tc.gridx = 0;
//			setLayout(new GridBagLayout());
			
//			JPanel up = new JPanel();
//			up.setPreferredSize(new Dimension(400, 150));
//			up.add(paraPanel);
			add(pPanel, BorderLayout.NORTH);
			add(sPanel, BorderLayout.CENTER);
//			add(up, tc);
//			tc.gridy = 1;
//			add(sPanel, tc);
//			dataSelectPanel = this;
			
			//TODO 最小化后最大化ParaPanel和显示部分公共ScrollPanel了,不该公用
			
		}
		
		
	}
	public class DataAddPanel extends JPanel{
		
		private static final long serialVersionUID = 1L;
		
		public DataAddPanel(){
			JPanel pPanel = new JPanel();//参数输入面板
			pPanel.setPreferredSize(new Dimension(500, 380));
			TitledBorder paraBorder = new TitledBorder("数据录入");
			pPanel.setBorder(paraBorder);
			AddPanel addPanel = AddPanel.getInstance();
			pPanel.add(addPanel);
			
			setLayout(new BorderLayout());
			add(pPanel, BorderLayout.NORTH);
			dataAddPanel = this;
		}
		
	}
}
