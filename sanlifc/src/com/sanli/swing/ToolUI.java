package com.sanli.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;


/**
 * 帮助系统编辑器
 * 
 * @author qifan.yang
 * 
 */
public class ToolUI extends JFrame {
	public static final int WIDTH = 1100;
	public static final int HEIGHT = 800;

	public static BufferedImage icon;
	public static ToolUI instancEditor;

	private static final long serialVersionUID = 1L;

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
		tab.add("查询数据",new JScrollPane(new DataSelectPanel()));
		tab.add("添加数据",new DataAddPanel());
		
		 add(tab, BorderLayout.CENTER);
		// add(editorPanel, BorderLayout.CENTER);
//		setSize(new Dimension(WIDTH, HEIGHT));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		instancEditor = this;

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
			JPanel pPanel = new JPanel();
			pPanel.setPreferredSize(new Dimension(getPreferredSize().width, 400));
			TitledBorder paraBorder = new TitledBorder("参数");
			pPanel.setBorder(paraBorder);
			ParaPanel paraPanel = new ParaPanel();
			pPanel.add(paraPanel);
			
			
			JPanel sPanel = new JPanel();
			TitledBorder showBorder = new TitledBorder("查询结果");
			sPanel.setBorder(showBorder);
			ShowPanel showPanel = new ShowPanel();
			sPanel.add(showPanel);
			
			setLayout(new BorderLayout());
			
			add(pPanel, BorderLayout.NORTH);
			add(sPanel, BorderLayout.CENTER);
			
		}
		
		
	}
	public class DataAddPanel extends JPanel{
		
		private static final long serialVersionUID = 1L;
		
	}
}
