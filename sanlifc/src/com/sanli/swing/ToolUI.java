package com.sanli.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sanli.logic.AppController;
import com.sanli.logic.AppWinUtils;
import com.sanli.logic.Utils;
import com.sanli.model.FCBean;



/**
 * ����ϵͳ�༭��
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
		setTitle("��ͬ������");
		setLayout(new BorderLayout());

		// create editor panel
		// EditorPanel editorPanel = new EditorPanel();

		 setJMenuBar(new TopMenu());
		// JToolBar toolBar = new CustomToolBar(editorPanel);

		JTabbedPane tab = new JTabbedPane();
		tab.add("��ѯ��ͬ",new DataSelectPanel());
		tab.add("��Ӻ�ͬ",new DataAddPanel());
		tab.add("ɾ����ͬ",new DeletePanel());
		
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
			pPanel.setPreferredSize(new Dimension(500, 380));
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
			
			//TODO ��С�������ParaPanel����ʾ���ֹ���ScrollPanel��,���ù���
			
		}
		
	}
	
	public class DataAddPanel extends JPanel{
		
		private static final long serialVersionUID = 1L;
		
		public DataAddPanel(){
			JPanel pPanel = new JPanel();//�����������
			pPanel.setPreferredSize(new Dimension(500, 380));
			TitledBorder paraBorder = new TitledBorder("����¼��");
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
			JLabel deleteLabel = new JLabel("ɾ�������Իָ�,������Ҫɾ����ͬ��[���] :");
			final JTextField deleteTextField = new JTextField(20);
			JButton okBtn = new JButton("ȷ��ɾ��");
			
			up.add(deleteLabel);
			up.add(deleteTextField);
			up.add(okBtn);
			up.setPreferredSize(new Dimension((int) getPreferredSize().getWidth(), 100));
			JPanel infoPanel = new JPanel();
			JLabel textField = new JLabel("������ʹ�ò�ѯ,Ȼ����ݲ�ѯ�����[���]ɾ������");
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
						AppWinUtils.showWarnMsg("���������!");
						return;
					}
					int id = Integer.parseInt(vs);
					FCBean have = AppController.getInstance().checkDeleAble(id);
					if(have == null){
						AppWinUtils.showWarnMsg("����Ų�����,����!");
						return;
					}
					//yes = 1, no = 0 or -1
					int result = JOptionPane.showConfirmDialog(ToolUI.getIntance(), "ȷ��ɾ������,�����Իָ�Ŷ", "����", JOptionPane.YES_NO_OPTION);
//					System.out.println(result);
					if(result == 0){
						 log.info("ȷ�� ɾ������, ��� = " + vs);
						 boolean success = AppController.getInstance().deleteOne(id);
						if(!success) {
							AppWinUtils.showWarnMsg("ɾ������ʧ��");
						} else {
							AppWinUtils.showWarnMsg("ɾ�����ݳɹ�");
						}
					}
				}
			});
			
		}
		
	}
}
