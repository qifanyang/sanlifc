package com.sanli.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;


import com.sanli.logic.Utils;
import com.sanli.model.FCBean;
import com.sanli.model.TextFieldObject;

/**
 * ��ѯ,�༭,���붼ʹ��������
 *
 */
public abstract class DataPanel extends JPanel{
//	private final static Log log = LogFactory.getLog(ParaPanel.class);

	private static final long serialVersionUID = 1L;
	
//	private static DataPanel instance = new DataPanel();
	
	public List<TextFieldObject> vlist = new ArrayList<TextFieldObject>();
	
	public GridBagConstraints tc = new GridBagConstraints();
	
	public DataPanel(){
		
		//UUID
		JTextField uuidTextField = new JTextField("");
		vlist.add(new TextFieldObject(uuidTextField, "uuid"));
		
//		GridBagConstraints tc = new GridBagConstraints();
		tc.fill = GridBagConstraints.BOTH;
		// c.fill = GridBagConstraints.REMAINDER;
		tc.weightx = 1.0;
		tc.weighty = 1.0;
		tc.gridy = 0;
		tc.gridx = 0;
		setLayout(new GridBagLayout());
		
		// ���̻�����Ϣ�������
		JPanel baseInfoPanel = new JPanel();
		baseInfoPanel.setBorder(new TitledBorder("���̻�����Ϣ"));
		baseInfoPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		// c.fill = GridBagConstraints.REMAINDER;
		c.weightx = 1.0;
		c.weighty = 1.0;

		c.gridy = 0;
		c.gridx = 0;
		JLabel numLabel = new JLabel("��ţ�", SwingConstants.RIGHT);
		JTextField numPara = new JTextField(10);
		baseInfoPanel.add(numLabel, c);
		c.gridx = 1;
		baseInfoPanel.add(numPara, c);
		vlist.add(new TextFieldObject(numPara, "id"));

		
		c.gridx = 2;
		JLabel cityLabel = new JLabel("���У�", SwingConstants.RIGHT);
		JTextField cityPara = new JTextField(10);
		baseInfoPanel.add(cityLabel, c);
		c.gridx = 3;
		baseInfoPanel.add(cityPara, c);
		vlist.add(new TextFieldObject(cityPara, "city"));

		c.gridy = 1;
		c.gridx = 0;
		JLabel yearLabel = new JLabel("��ȣ�", SwingConstants.RIGHT);
		JTextField yearPara = new JTextField(10);
		baseInfoPanel.add(yearLabel, c);
		c.gridx = 1;
		baseInfoPanel.add(yearPara, c);
		vlist.add(new TextFieldObject(yearPara, "year"));

		c.gridx = 2;
		JLabel cptLabel = new JLabel("	Ժϵͳ��������ƣ�", SwingConstants.RIGHT);
		JTextField cptPara = new JTextField(10);
		baseInfoPanel.add(cptLabel, c);
		c.gridx = 3;
		baseInfoPanel.add(cptPara, c);
		vlist.add(new TextFieldObject(cptPara, "create_project_title"));
		
		c.gridy = 2;
		c.gridx = 0;
		JLabel cpnLabel = new JLabel("Ժϵͳ����̱�ţ�", SwingConstants.RIGHT);
		JTextField cpnPara = new JTextField(10);
		baseInfoPanel.add(cpnLabel, c);
		c.gridx = 1;
		baseInfoPanel.add(cpnPara, c);
		vlist.add(new TextFieldObject(cpnPara, "create_project_number"));

		c.gridx = 2;
		JLabel pnLabel = new JLabel("��������(�׷���������)��", SwingConstants.RIGHT);
		JTextField pnPara = new JTextField(10);
		baseInfoPanel.add(pnLabel, c);
		c.gridx = 3;
		baseInfoPanel.add(pnPara, c);
		vlist.add(new TextFieldObject(pnPara, "project_name"));

		c.gridy = 3;
		c.gridx = 0;
		JLabel misLabel = new JLabel("MIS��ţ�", SwingConstants.RIGHT);
		JTextField misPara = new JTextField(10);
		baseInfoPanel.add(misLabel, c);
		c.gridx = 1;
		baseInfoPanel.add(misPara, c);
		vlist.add(new TextFieldObject(misPara, "mis_number"));

		JLabel rcnLabel = new JLabel("Ժ��ͬ�ţ�", SwingConstants.RIGHT);
		JTextField rcnPara = new JTextField(10);
		c.gridx = 2;
		baseInfoPanel.add(rcnLabel, c);
		c.gridx = 3;
		baseInfoPanel.add(rcnPara, c);
		vlist.add(new TextFieldObject(rcnPara, "research_contract_number"));

		c.gridy = 4;
		JLabel snLabel = new JLabel("�򻯹������ƣ�", SwingConstants.RIGHT);
		JTextField snPara = new JTextField(10);
		c.gridx = 0;
		baseInfoPanel.add(snLabel, c);
		c.gridx = 1;
		baseInfoPanel.add(snPara, c);
		vlist.add(new TextFieldObject(snPara, "simple_name"));
		
		JLabel pmtLabel = new JLabel("���̴��ࣺ", SwingConstants.RIGHT);
		JTextField pmtPara = new JTextField(10);
		c.gridx = 2;
		baseInfoPanel.add(pmtLabel, c);
		c.gridx = 3;
		baseInfoPanel.add(pmtPara, c);
		vlist.add(new TextFieldObject(pmtPara, "project_main_type"));
		
		
		c.gridy = 5;
		JLabel ptLabel = new JLabel("�������", SwingConstants.RIGHT);
		JTextField ptPara = new JTextField(10);
		c.gridx = 0;
		baseInfoPanel.add(ptLabel, c);
		c.gridx = 1;
		baseInfoPanel.add(ptPara, c);
		vlist.add(new TextFieldObject(ptPara, "project_type"));
		
		JLabel cpiLabel = new JLabel("���������", SwingConstants.RIGHT);
		JTextField cpiPara = new JTextField(10);
		c.gridx = 2;
		baseInfoPanel.add(cpiLabel, c);
		c.gridx = 3;
		baseInfoPanel.add(cpiPara, c);
		vlist.add(new TextFieldObject(cpiPara, "create_project_info"));
		
		
		c.gridy = 6;
		JLabel apmLabel = new JLabel("�׷���Ŀ������", SwingConstants.RIGHT);
		JTextField apmPara = new JTextField(10);
		c.gridx = 0;
		baseInfoPanel.add(apmLabel, c);
		c.gridx = 1;
		baseInfoPanel.add(apmPara, c);
		vlist.add(new TextFieldObject(apmPara, "a_project_manager"));
		
		JLabel plLabel = new JLabel("��Ŀ����С�飺", SwingConstants.RIGHT);
		JTextField plPara = new JTextField(10);
		c.gridx = 2;
		baseInfoPanel.add(plLabel, c);
		c.gridx = 3;
		baseInfoPanel.add(plPara, c);
		vlist.add(new TextFieldObject(plPara, "project_leader"));
		
		c.gridy = 7;
		JLabel cpayLabel = new JLabel("��ͬ�涨����׶Σ�", SwingConstants.RIGHT);
		JTextField cpayPara = new JTextField(10);
		c.gridx = 0;
		baseInfoPanel.add(cpayLabel, c);
		c.gridx = 1;
//		c.weightx = 2;
		baseInfoPanel.add(cpayPara, c);
		vlist.add(new TextFieldObject(cpayPara, "contract_pay_info"));
		
		
		add(baseInfoPanel, tc);
		
		////==============��ͬ���=============================
		JPanel contractInfoPanel = new JPanel();
		contractInfoPanel.setBorder(new TitledBorder("��ͬ���-��̽��Ʒ�"));
		contractInfoPanel.setLayout(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();
		c1.fill = GridBagConstraints.BOTH;
		// c.fill = GridBagConstraints.REMAINDER;
		c1.weightx = 1.0;
		c1.weighty = 1.0;
		
		
		JLabel ptiLabel = new JLabel("������Ͷ�ʣ���λ:Ԫ����", SwingConstants.RIGHT);
		JTextField ptiPara = new JTextField(10);
		contractInfoPanel.add(ptiLabel, c1);
		c1.gridx = 1;
		contractInfoPanel.add(ptiPara, c1);
		vlist.add(new TextFieldObject(ptiPara, "project_total_invest"));

		
		c1.gridx = 2;
		JLabel htwLabel = new JLabel("���߲��֣�", SwingConstants.RIGHT);
		JTextField htwPara = new JTextField(10);
		contractInfoPanel.add(htwLabel, c1);
		c1.gridx = 3;
		contractInfoPanel.add(htwPara, c1);
		vlist.add(new TextFieldObject(htwPara, "ht_wireless"));
		
		
		c1.gridy = 1;
		c1.gridx = 0;
		JLabel htcLabel = new JLabel("���䲿�֣�", SwingConstants.RIGHT);
		JTextField htcPara = new JTextField(10);
		contractInfoPanel.add(htcLabel, c1);
		c1.gridx = 1;
		contractInfoPanel.add(htcPara, c1);
		vlist.add(new TextFieldObject(htcPara, "ht_transmission"));

		
		c1.gridx = 2;
		JLabel htpLabel = new JLabel("��Դ���֣�", SwingConstants.RIGHT);
		JTextField htpPara = new JTextField(10);
		contractInfoPanel.add(htpLabel, c1);
		c1.gridx = 3;
		contractInfoPanel.add(htpPara, c1);
		vlist.add(new TextFieldObject(htpPara, "ht_power"));
		
		c1.gridy = 2;
		c1.gridx = 0;
		JLabel htcivilLabel = new JLabel("�������֣�", SwingConstants.RIGHT);
		JTextField htcivilPara = new JTextField(10);
		contractInfoPanel.add(htcivilLabel, c1);
		c1.gridx = 1;
		contractInfoPanel.add(htcivilPara, c1);
		vlist.add(new TextFieldObject(htcivilPara, "ht_civil"));

		
		c1.gridx = 2;
		JLabel httLabel = new JLabel("�ϼƣ�", SwingConstants.RIGHT);
		JTextField httPara = new JTextField(10);
		contractInfoPanel.add(httLabel, c1);
		c1.gridx = 3;
		contractInfoPanel.add(httPara, c1);
		vlist.add(new TextFieldObject(httPara, "ht_total"));
		
		c1.gridy = 3;
		contractInfoPanel.add(new JLabel(" "), c1);
		c1.gridy = 4; 
		contractInfoPanel.add(new JLabel(" "), c1);
		c1.gridy = 5; 
		contractInfoPanel.add(new JLabel(" "), c1);
		c1.gridy = 6;
		contractInfoPanel.add(new JLabel(" "), c1);
		c1.gridy = 7;
		contractInfoPanel.add(new JLabel(" "), c1);
		
		
		tc.gridx = 1;
		add(contractInfoPanel, tc);
		
		
		//��ͬ����һ����========
		JPanel contractPanel = new JPanel();
		contractPanel.setBorder(new TitledBorder("��ͬ�������һ����"));
		contractPanel.setLayout(new GridBagLayout());
		GridBagConstraints c2 = new GridBagConstraints();
		c2.fill = GridBagConstraints.BOTH;
		// c.fill = GridBagConstraints.REMAINDER;
		c2.weightx = 1.0;
		c2.weighty = 1.0;
		
		c2.gridy = 0;
		c2.gridx = 0;
		JLabel atimeLabel = new JLabel("��ͬ�ύ��Ŀ����-ʱ�䣺", SwingConstants.RIGHT);
		final JTextField atimePara = new JTextField(10);
		contractPanel.add(atimeLabel, c2);
		c2.gridx = 1;
		contractPanel.add(atimePara, c2);
		vlist.add(new TextFieldObject(atimePara, "a_time"));
		
		c2.gridx = 2;
		JLabel anoteLabel = new JLabel("��ͬ�ύ��Ŀ����-��ע��", SwingConstants.RIGHT);
		JTextField anotePara = new JTextField(10);
		contractPanel.add(anoteLabel, c2);
		c2.gridx = 3;
		contractPanel.add(anotePara, c2);
		vlist.add(new TextFieldObject(anotePara, "a_note"));
		
		c2.gridy = 1;
		c2.gridx = 0;
		JLabel btimeLabel = new JLabel("�׷�ˮӡ���ͬ����-ʱ�䣺", SwingConstants.RIGHT);
		JTextField btimePara = new JTextField(10);
		contractPanel.add(btimeLabel, c2);
		c2.gridx = 1;
		contractPanel.add(btimePara, c2);
		vlist.add(new TextFieldObject(btimePara, "b_time"));

		
		c2.gridx = 2;
		JLabel bnoteLabel = new JLabel("�׷�ˮӡ���ͬ����-��ע��", SwingConstants.RIGHT);
		JTextField bnotePara = new JTextField(10);
		contractPanel.add(bnoteLabel, c2);
		c2.gridx = 3;
		contractPanel.add(bnotePara, c2);
		vlist.add(new TextFieldObject(bnotePara, "b_note"));
		
		c2.gridy = 2;
		c2.gridx = 0;
		JLabel ctimeLabel = new JLabel("�ύ��Ժ�г���-ʱ�䣺", SwingConstants.RIGHT);
		JTextField ctimePara = new JTextField(10);
		contractPanel.add(ctimeLabel, c2);
		c2.gridx = 1;
		contractPanel.add(ctimePara, c2);
		vlist.add(new TextFieldObject(ctimePara, "c_time"));

		
		c2.gridx = 2;
		JLabel cnoteLabel = new JLabel("�ύ��Ժ�г���-��ע��", SwingConstants.RIGHT);
		JTextField cnotePara = new JTextField(10);
		contractPanel.add(cnoteLabel, c2);
		c2.gridx = 3;
		contractPanel.add(cnotePara, c2);
		vlist.add(new TextFieldObject(cnotePara, "c_note"));
		
		c2.gridy = 3;
		c2.gridx = 0;
		JLabel dtimeLabel = new JLabel("Ժ�г�������ֽ�ʺ�ͬ-ʱ�䣺", SwingConstants.RIGHT);
		JTextField dtimePara = new JTextField(10);
		contractPanel.add(dtimeLabel, c2);
		c2.gridx = 1;
		contractPanel.add(dtimePara, c2);
		vlist.add(new TextFieldObject(dtimePara, "d_time"));

		
		c2.gridx = 2;
		JLabel dnoteLabel = new JLabel("Ժ�г�������ֽ�ʺ�ͬ-��ע��", SwingConstants.RIGHT);
		JTextField dnotePara = new JTextField(10);
		contractPanel.add(dnoteLabel, c2);
		c2.gridx = 3;
		contractPanel.add(dnotePara, c2);
		vlist.add(new TextFieldObject(dnotePara, "d_note"));
		
		c2.gridy = 4;
		c2.gridx = 0;
		JLabel etimeLabel = new JLabel("ֽ�ʺ�ͬ�ύ��Ŀ����-ʱ�䣺", SwingConstants.RIGHT);
		JTextField etimePara = new JTextField(10);
		contractPanel.add(etimeLabel, c2);
		c2.gridx = 1;
		contractPanel.add(etimePara, c2);
		vlist.add(new TextFieldObject(etimePara, "e_time"));

		
		c2.gridx = 2;
		JLabel enoteLabel = new JLabel("ֽ�ʺ�ͬ�ύ��Ŀ����-��ע��", SwingConstants.RIGHT);
		JTextField enotePara = new JTextField(10);
		contractPanel.add(enoteLabel, c2);
		c2.gridx = 3;
		contractPanel.add(enotePara, c2);
		vlist.add(new TextFieldObject(enotePara,"e_note"));
		
		
		c2.gridy = 5;
		c2.gridx = 0;
		JLabel ftimeLabel = new JLabel("��ʽ���ͬ�Ļ�Ժ�г���-ʱ�䣺", SwingConstants.RIGHT);
		JTextField ftimePara = new JTextField(10);
		contractPanel.add(ftimeLabel, c2);
		c2.gridx = 1;
		contractPanel.add(ftimePara, c2);
		vlist.add(new TextFieldObject(ftimePara, "f_time"));

		
		c2.gridx = 2;
		JLabel fnoteLabel = new JLabel("��ʽ���ͬ�Ļ�Ժ�г���-��ע��", SwingConstants.RIGHT);
		JTextField fnotePara = new JTextField(10);
		contractPanel.add(fnoteLabel, c2);
		c2.gridx = 3;
		contractPanel.add(fnotePara, c2);
		vlist.add(new TextFieldObject(fnotePara, "f_note"));
		
		c2.gridx = 0;
		c2.gridy = 6;
		contractInfoPanel.add(new JLabel(" "), c2);
		c2.gridy = 7;
		contractInfoPanel.add(new JLabel(" "), c2);
		
		tc.gridx = 2;
		add(contractPanel, tc);
		
		//������==============
		JPanel finalPanel = new JPanel();
		finalPanel.setBorder(new TitledBorder("������-��̽��Ʒ�"));
		finalPanel.setLayout(new GridBagLayout());
		GridBagConstraints c3 = new GridBagConstraints();
		c3.fill = GridBagConstraints.BOTH;
		// c.fill = GridBagConstraints.REMAINDER;
		c3.weightx = 1.0;
		c3.weighty = 1.0;
		
		c3.gridy = 0;
		c3.gridx = 0;
		JLabel fctimeLabel = new JLabel("�������ʱ�䣺", SwingConstants.RIGHT);
		JTextField fctimePara = new JTextField(10);
		finalPanel.add(fctimeLabel, c3);
		c3.gridx = 1;
		finalPanel.add(fctimePara, c3);
		vlist.add(new TextFieldObject(fctimePara, "final_cost_time"));

		
		c3.gridx = 2;
		JLabel fwLabel = new JLabel("���߲��֣�", SwingConstants.RIGHT);
		JTextField fwPara = new JTextField(10);
		finalPanel.add(fwLabel, c3);
		c3.gridx = 3;
		finalPanel.add(fwPara, c3);
		vlist.add(new TextFieldObject(fwPara, "final_wireless"));
		
		c3.gridy = 1;
		c3.gridx = 0;
		JLabel fcLabel = new JLabel("���䲿�֣�", SwingConstants.RIGHT);
		JTextField fcPara = new JTextField(10);
		finalPanel.add(fcLabel, c3);
		c3.gridx = 1;
		finalPanel.add(fcPara, c3);
		vlist.add(new TextFieldObject(fcPara, "final_transmission"));

		
		c3.gridx = 2;
		JLabel fpLabel = new JLabel("��Դ���֣�", SwingConstants.RIGHT);
		JTextField fpPara = new JTextField(10);
		finalPanel.add(fpLabel, c3);
		c3.gridx = 3;
		finalPanel.add(fpPara, c3);
		vlist.add(new TextFieldObject(fpPara, "final_power"));
		
		c3.gridy = 2;
		c3.gridx = 0;
		JLabel fcivilLabel = new JLabel("�������֣�", SwingConstants.RIGHT);
		JTextField fcivilPara = new JTextField(10);
		finalPanel.add(fcivilLabel, c3);
		c3.gridx = 1;
		finalPanel.add(fcivilPara, c3);
		vlist.add(new TextFieldObject(fcivilPara, "final_civil"));

		
		c3.gridx = 2;
		JLabel ftotalLabel = new JLabel("�ϼƣ�", SwingConstants.RIGHT);
		JTextField ftotalPara = new JTextField(10);
		finalPanel.add(ftotalLabel, c3);
		c3.gridx = 3;
		finalPanel.add(ftotalPara, c3);
		vlist.add(new TextFieldObject(ftotalPara, "final_total"));
		
		tc.gridy = 1;
		tc.gridx = 0;
		add(finalPanel, tc);
		
		//��Ʊ�������һ����============
		JPanel fapiaoPanel = new JPanel();
		fapiaoPanel.setBorder(new TitledBorder("��Ʊ�������һ����"));
		fapiaoPanel.setLayout(new GridBagLayout());
		GridBagConstraints c4 = new GridBagConstraints();
		c4.fill = GridBagConstraints.BOTH;
		// c.fill = GridBagConstraints.REMAINDER;
		c4.weightx = 1.0;
		c4.weighty = 1.0;
		
		c4.gridy = 0;
		c4.gridx = 0;
		JLabel fatimeLabel = new JLabel("��һ��-ʱ�䣺", SwingConstants.RIGHT);
		JTextField fatimePara = new JTextField(10);
		fapiaoPanel.add(fatimeLabel, c4);
		c4.gridx = 1;
		fapiaoPanel.add(fatimePara, c4);
		vlist.add(new TextFieldObject(fatimePara, "fapiao_a_time"));

		
		c4.gridx = 2;
		JLabel fascaleLabel = new JLabel("��һ��-��������", SwingConstants.RIGHT);
		JTextField fascalePara = new JTextField(10);
		fapiaoPanel.add(fascaleLabel, c4);
		c4.gridx = 3;
		fapiaoPanel.add(fascalePara, c4);
		vlist.add(new TextFieldObject(fascalePara, "fapiao_a_scale"));
		
		c4.gridx = 4;
		JLabel famoneyLabel = new JLabel("��һ��-��", SwingConstants.RIGHT);
		JTextField famoneyPara = new JTextField(10);
		fapiaoPanel.add(famoneyLabel, c4);
		c4.gridx = 5;
		fapiaoPanel.add(famoneyPara, c4);
		vlist.add(new TextFieldObject(famoneyPara, "fapiao_a_money"));
		
		c4.gridy = 1;
		c4.gridx = 0;
		JLabel fbtimeLabel = new JLabel("�ڶ���-ʱ�䣺", SwingConstants.RIGHT);
		JTextField fbtimePara = new JTextField(10);
		fapiaoPanel.add(fbtimeLabel, c4);
		c4.gridx = 1;
		fapiaoPanel.add(fbtimePara, c4);
		vlist.add(new TextFieldObject(fbtimePara, "fapiao_b_time"));

		
		c4.gridx = 2;
		JLabel fbscaleLabel = new JLabel("�ڶ���-��������", SwingConstants.RIGHT);
		JTextField fbscalePara = new JTextField(10);
		fapiaoPanel.add(fbscaleLabel, c4);
		c4.gridx = 3;
		fapiaoPanel.add(fbscalePara, c4);
		vlist.add(new TextFieldObject(fbscalePara, "fapiao_b_scale"));
		
		c4.gridx = 4;
		JLabel fbmoneyLabel = new JLabel("�ڶ���-��", SwingConstants.RIGHT);
		JTextField fbmoneyPara = new JTextField(10);
		fapiaoPanel.add(fbmoneyLabel, c4);
		c4.gridx = 5;
		fapiaoPanel.add(fbmoneyPara, c4);
		vlist.add(new TextFieldObject(fbmoneyPara, "fapiao_b_money"));
		
		
		c4.gridy = 2;
		c4.gridx = 0;
		JLabel fctLabel = new JLabel("������-ʱ�䣺", SwingConstants.RIGHT);
		JTextField fctPara = new JTextField(10);
		fapiaoPanel.add(fctLabel, c4);
		c4.gridx = 1;
		fapiaoPanel.add(fctPara, c4);
		vlist.add(new TextFieldObject(fctPara, "fapiao_c_time"));

		
		c4.gridx = 2;
		JLabel fcscaleLabel = new JLabel("������-��������", SwingConstants.RIGHT);
		JTextField fcscalePara = new JTextField(10);
		fapiaoPanel.add(fcscaleLabel, c4);
		c4.gridx = 3;
		fapiaoPanel.add(fcscalePara, c4);
		vlist.add(new TextFieldObject(fcscalePara, "fapiao_c_scale"));
		
		c4.gridx = 4;
		JLabel fcmoneyLabel = new JLabel("������-��", SwingConstants.RIGHT);
		JTextField fcmoneyPara = new JTextField(10);
		fapiaoPanel.add(fcmoneyLabel, c4);
		c4.gridx = 5;
		fapiaoPanel.add(fcmoneyPara, c4);
		vlist.add(new TextFieldObject(fcmoneyPara, "fapiao_c_money"));
		
		tc.gridx = 1;
		add(fapiaoPanel,tc);
		
		//������������ͱ�ע==========
		JPanel overlPanel = new JPanel();
		overlPanel.setBorder(new TitledBorder("����"));
		overlPanel.setLayout(new GridBagLayout());
		GridBagConstraints c5 = new GridBagConstraints();
		c5.fill = GridBagConstraints.BOTH;
		// c.fill = GridBagConstraints.REMAINDER;
		c5.weightx = 1.0;
		c5.weighty = 1.0;
		
		c5.gridy = 0;
		c5.gridx = 0;
		JLabel projectAcceptLabel = new JLabel("�������������", SwingConstants.RIGHT);
		JTextField projectAcceptPara = new JTextField(10);
		overlPanel.add(projectAcceptLabel, c5);
		c5.gridx = 1;
		overlPanel.add(projectAcceptPara, c5);
		vlist.add(new TextFieldObject(projectAcceptPara, "check_info"));

		
		c5.gridx = 2;
		JLabel projectNoteLabel = new JLabel("��ע��", SwingConstants.RIGHT);
		JTextField projectNotePara = new JTextField(10);
		overlPanel.add(projectNoteLabel, c5);
		c5.gridx = 3;
		overlPanel.add(projectNotePara, c5);
		vlist.add(new TextFieldObject(projectNotePara, "remark"));
		
		tc.gridx = 2;
		add(overlPanel, tc);
		
		
//		JPanel btnPanel = new JPanel();
//		JButton selectBtn = new JButton("ȷ������...");
//		JButton resetBtn = new JButton("������д...");
//		btnPanel.add(selectBtn);
//		btnPanel.add(resetBtn);
//		tc.fill = GridBagConstraints.CENTER;
//		tc.gridy = 2;
//		tc.gridx = 1;
//		add(btnPanel, tc);
		
		addDatePickerEvent();
	}
	
	
	public List<TextFieldObject> getVList(){
		return vlist;
	}
	
	private void addDatePickerEvent(){
		for(final TextFieldObject tfo : this.vlist){
			tfo.getTextField().addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					super.mousePressed(e);
					FCBean fcBean = new FCBean();
					try {
						Field field = fcBean.getClass().getField(tfo.getName());
						if(field.getType() == long.class){
							DatePickerFactory.showDatePicker(tfo.getTextField(), 0, 0);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					} 
				}
			});
			
			
		}
	}
	
	public void fillData(FCBean bean) throws Exception{
		for (TextFieldObject tfo : vlist) {
			Field f = bean.getClass().getField(tfo.getName());
			Class<?> type = f.getType();
			if (type == int.class) {
				tfo.setVlaue(String.valueOf(f.getInt(bean)));
			} else if (type == long.class) {
				tfo.setVlaue(Utils.millisecondToDate(f.getLong(bean)));
			} else if (type == float.class) {
				tfo.setVlaue(String.valueOf(f.getFloat(bean)));
			} else if (type == String.class) {
				Object obj = f.get(bean);
				if (obj == null) {
					tfo.setVlaue("");
				} else {
					tfo.setVlaue(String.valueOf(f.get(bean)));
				}
			}
		}
	}
}