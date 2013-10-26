package com.sanli.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sanli.logic.AppController;
import com.sanli.model.FCBean;
import com.sanli.util.LanguageLoader;



/**
 * 帮助系统编辑器
 * 
 * @author qifan.yang
 * 
 */
public class ToolUI extends JFrame {
	private final static Log log = LogFactory.getLog(ToolUI.class);
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
		setTitle("合同管理工具");
		setLayout(new BorderLayout());

		// create editor panel
		// EditorPanel editorPanel = new EditorPanel();

		 setJMenuBar(new TopMenu());
		// JToolBar toolBar = new CustomToolBar(editorPanel);

		JTabbedPane tab = new JTabbedPane();
		tab.add("查询合同",new DataSelectPanel());
		tab.add("添加合同",new DataAddPanel());
		tab.add("删除合同",new DeletePanel());
		tab.add("使用说明",new UseInfoPanel());
		
		getContentPane().add(tab, BorderLayout.CENTER);
		// add(editorPanel, BorderLayout.CENTER);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(dimension.width, dimension.height - 10);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
//		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		instancEditor = this;
//		setResizable(false);
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				ShowPanel.getInstance().setPopMenu(false);
				ToolUI.this.pack();
			}
		});
		
		
		//初始化其它
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				LanguageLoader.getInstance().init();
			}
		}).start();
		

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
			add(pPanel, BorderLayout.NORTH);
			add(sPanel, BorderLayout.CENTER);
			
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
	
	public class DeletePanel extends JPanel{
		private static final long serialVersionUID = 1L;
		
		public DeletePanel(){
			JPanel up = new JPanel();
			JLabel deleteLabel = new JLabel("删除不可以恢复,请输入要删除合同的[序号] :");
			final JTextField deleteTextField = new JTextField(20);
			JButton okBtn = new JButton("确定删除");
			
			up.add(deleteLabel);
			up.add(deleteTextField);
			up.add(okBtn);
			up.setPreferredSize(new Dimension((int) getPreferredSize().getWidth(), 100));
			JPanel infoPanel = new JPanel();
			JLabel textField = new JLabel("建议先使用查询,然后根据查询结果的[序号]删除数据");
			textField.setForeground(Color.red);
			infoPanel.add(textField);
			
			setLayout(new BorderLayout());
			add(up, BorderLayout.NORTH);
			add(infoPanel, BorderLayout.CENTER);
			
			
			okBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String vs = deleteTextField.getText().trim();
					if(vs.length() == 0){
						AppWinUtils.showWarnMsg("请输入序号!");
						return;
					}
					int id = Integer.parseInt(vs);
					FCBean have = AppController.getInstance().checkDeleAble(id);
					if(have == null){
						AppWinUtils.showWarnMsg("该序号不存在,请检查!");
						return;
					}
					//yes = 1, no = 0 or -1
					int result = JOptionPane.showConfirmDialog(ToolUI.getIntance(), "确定删除数据,不可以恢复哦", "警告", JOptionPane.YES_NO_OPTION);
//					System.out.println(result);
					if(result == 0){
						 log.info("确认 删除数据, 序号 = " + vs);
						 boolean success = AppController.getInstance().deleteOne(id);
						if(!success) {
							AppWinUtils.showWarnMsg("删除数据失败");
						} else {
							AppWinUtils.showWarnMsg("删除数据成功");
						}
					}
				}
			});
			
		}
		
	}
	
	public static class UseInfoPanel extends JPanel{

		private static final long serialVersionUID = 1L;
		
		public UseInfoPanel(){
			String info = "" +
					"查找合同:\n" +
					"	1.查询按钮--->在文本框内输入所有条件,点击中间部分的查询按钮,将在表格里面显示查询结果,不输入条件默认查询所有数据,选中表格一行,右击鼠标弹出窗口可以删除和编辑该合同\n" +
					"	2.重置按钮--->点击重置按钮,将清除所有的查询条件\n" +
					"	3.导出数据--->点击导出数据,将导出查询的结果(下半部分表格),导出的文本文件可直接复制到Excel中查看(Ctr+A然后Ctr+V即可)\n" +
					"	4.导入数据--->暂时没有实现\n" +
					"添加合同:\n" +
					"	1.确定添加--->在文本框数据要新添加的内容,点击确定添加,将保存至数据库中,注意:需要填写数字的不能填写字母,时间填写直接选择不要手动修改\n" +
					"	2.重新填写--->清除所有填写的内容";
			setLayout(new BorderLayout());
			JTextArea textArea = new JTextArea(info);
			textArea.setEditable(false);
			add(textArea, BorderLayout.CENTER);
		}
		
	}
}
