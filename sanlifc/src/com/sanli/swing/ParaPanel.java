package com.sanli.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

/**
 * 参数面板
 *
 * @author XF
 * 2013-10-21 下午10:17:47
 */
public class ParaPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public ParaPanel(){
		
		GridBagConstraints tc = new GridBagConstraints();
		tc.fill = GridBagConstraints.BOTH;
		// c.fill = GridBagConstraints.REMAINDER;
		tc.weightx = 1.0;
		tc.weighty = 1.0;
		tc.gridy = 0;
		tc.gridx = 0;
		setLayout(new GridBagLayout());
		
		// 工程基本信息参数面板
		JPanel baseInfoPanel = new JPanel();
		baseInfoPanel.setBorder(new TitledBorder("工程基本信息"));
		baseInfoPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		// c.fill = GridBagConstraints.REMAINDER;
		c.weightx = 1.0;
		c.weighty = 1.0;

		c.gridy = 0;
		c.gridx = 0;
		JLabel numLabel = new JLabel("序号：", SwingConstants.RIGHT);
		JTextField numPara = new JTextField(10);
		baseInfoPanel.add(numLabel, c);
		c.gridx = 1;
		baseInfoPanel.add(numPara, c);

		
		c.gridx = 2;
		JLabel cityLabel = new JLabel("地市：", SwingConstants.RIGHT);
		JTextField cityPara = new JTextField(10);
		baseInfoPanel.add(cityLabel, c);
		c.gridx = 3;
		baseInfoPanel.add(cityPara, c);

		c.gridy = 1;
		c.gridx = 0;
		JLabel yearLabel = new JLabel("年度：", SwingConstants.RIGHT);
		JTextField yearPara = new JTextField(10);
		baseInfoPanel.add(yearLabel, c);
		c.gridx = 1;
		baseInfoPanel.add(yearPara, c);

		c.gridx = 2;
		JLabel cptLabel = new JLabel("	院系统立项工程名称：", SwingConstants.RIGHT);
		JTextField cptPara = new JTextField(10);
		baseInfoPanel.add(cptLabel, c);
		c.gridx = 3;
		baseInfoPanel.add(cptPara, c);
		
		c.gridy = 2;
		c.gridx = 0;
		JLabel cpnLabel = new JLabel("院系统立项工程编号：", SwingConstants.RIGHT);
		JTextField cpnPara = new JTextField(10);
		baseInfoPanel.add(cpnLabel, c);
		c.gridx = 1;
		baseInfoPanel.add(cpnPara, c);

		c.gridx = 2;
		JLabel pnLabel = new JLabel("工程名称(甲方立项名称)：", SwingConstants.RIGHT);
		JTextField pnPara = new JTextField(10);
		baseInfoPanel.add(pnLabel, c);
		c.gridx = 3;
		baseInfoPanel.add(pnPara, c);

		c.gridy = 3;
		c.gridx = 0;
		JLabel misLabel = new JLabel("MIS编号：", SwingConstants.RIGHT);
		JTextField misPara = new JTextField(10);
		baseInfoPanel.add(misLabel, c);
		c.gridx = 1;
		baseInfoPanel.add(misPara, c);

		JLabel rcnLabel = new JLabel("院合同号：", SwingConstants.RIGHT);
		JTextField rcnPara = new JTextField(10);
		c.gridx = 2;
		baseInfoPanel.add(rcnLabel, c);
		c.gridx = 3;
		baseInfoPanel.add(rcnPara, c);

		c.gridy = 4;
		JLabel snLabel = new JLabel("简化工程名称：", SwingConstants.RIGHT);
		JTextField snPara = new JTextField(10);
		c.gridx = 0;
		baseInfoPanel.add(snLabel, c);
		c.gridx = 1;
		baseInfoPanel.add(snPara, c);
		
		JLabel pmtLabel = new JLabel("工程大类：", SwingConstants.RIGHT);
		JTextField pmtPara = new JTextField(10);
		c.gridx = 2;
		baseInfoPanel.add(pmtLabel, c);
		c.gridx = 3;
		baseInfoPanel.add(pmtPara, c);
		
		
		c.gridy = 5;
		JLabel ptLabel = new JLabel("工程类别：", SwingConstants.RIGHT);
		JTextField ptPara = new JTextField(10);
		c.gridx = 0;
		baseInfoPanel.add(ptLabel, c);
		c.gridx = 1;
		baseInfoPanel.add(ptPara, c);
		
		JLabel cpiLabel = new JLabel("立项情况：", SwingConstants.RIGHT);
		JTextField cpiPara = new JTextField(10);
		c.gridx = 2;
		baseInfoPanel.add(cpiLabel, c);
		c.gridx = 3;
		baseInfoPanel.add(cpiPara, c);
		
		
		c.gridy = 6;
		JLabel apmLabel = new JLabel("甲方项目经理：", SwingConstants.RIGHT);
		JTextField apmPara = new JTextField(10);
		c.gridx = 0;
		baseInfoPanel.add(apmLabel, c);
		c.gridx = 1;
		baseInfoPanel.add(apmPara, c);
		
		JLabel plLabel = new JLabel("项目负责小组：", SwingConstants.RIGHT);
		JTextField plPara = new JTextField(10);
		c.gridx = 2;
		baseInfoPanel.add(plLabel, c);
		c.gridx = 3;
		baseInfoPanel.add(plPara, c);
		
		c.gridy = 7;
		JLabel cpayLabel = new JLabel("合同规定付款阶段：", SwingConstants.RIGHT);
		JTextField cpayPara = new JTextField(10);
		c.gridx = 0;
		baseInfoPanel.add(cpayLabel, c);
		c.gridx = 1;
//		c.weightx = 2;
		baseInfoPanel.add(cpayPara, c);
		
		
		add(baseInfoPanel, tc);
		
		////==============合同金额=============================
		JPanel contractInfoPanel = new JPanel();
		contractInfoPanel.setBorder(new TitledBorder("合同金额-勘探设计费"));
		contractInfoPanel.setLayout(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();
		c1.fill = GridBagConstraints.BOTH;
		// c.fill = GridBagConstraints.REMAINDER;
		c1.weightx = 1.0;
		c1.weighty = 1.0;
		
		
		JLabel ptiLabel = new JLabel("工程总投资（单位:元）：", SwingConstants.RIGHT);
		JTextField ptiPara = new JTextField(10);
		contractInfoPanel.add(ptiLabel, c1);
		c1.gridx = 1;
		contractInfoPanel.add(ptiPara, c1);

		
		c1.gridx = 2;
		JLabel htwLabel = new JLabel("无线部分：", SwingConstants.RIGHT);
		JTextField htwPara = new JTextField(10);
		contractInfoPanel.add(htwLabel, c1);
		c1.gridx = 3;
		contractInfoPanel.add(htwPara, c1);
		
		
		c1.gridy = 1;
		c1.gridx = 0;
		JLabel htcLabel = new JLabel("传输部分：", SwingConstants.RIGHT);
		JTextField htcPara = new JTextField(10);
		contractInfoPanel.add(htcLabel, c1);
		c1.gridx = 1;
		contractInfoPanel.add(htcPara, c1);

		
		c1.gridx = 2;
		JLabel htpLabel = new JLabel("电源部分：", SwingConstants.RIGHT);
		JTextField htpPara = new JTextField(10);
		contractInfoPanel.add(htpLabel, c1);
		c1.gridx = 3;
		contractInfoPanel.add(htpPara, c1);
		
		c1.gridy = 2;
		c1.gridx = 0;
		JLabel htcivilLabel = new JLabel("土建部分：", SwingConstants.RIGHT);
		JTextField htcivilPara = new JTextField(10);
		contractInfoPanel.add(htcivilLabel, c1);
		c1.gridx = 1;
		contractInfoPanel.add(htcivilPara, c1);

		
		c1.gridx = 2;
		JLabel httLabel = new JLabel("合计：", SwingConstants.RIGHT);
		JTextField httPara = new JTextField(10);
		contractInfoPanel.add(httLabel, c1);
		c1.gridx = 3;
		contractInfoPanel.add(httPara, c1);
		
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
		
		
		//合同流程一览表========
		JPanel contractPanel = new JPanel();
		contractPanel.setBorder(new TitledBorder("合同流程情况一览表"));
		contractPanel.setLayout(new GridBagLayout());
		GridBagConstraints c2 = new GridBagConstraints();
		c2.fill = GridBagConstraints.BOTH;
		// c.fill = GridBagConstraints.REMAINDER;
		c2.weightx = 1.0;
		c2.weighty = 1.0;
		
		c2.gridy = 0;
		c2.gridx = 0;
		JLabel atimeLabel = new JLabel("合同提交项目经理-时间：", SwingConstants.RIGHT);
		JTextField atimePara = new JTextField(10);
		contractPanel.add(atimeLabel, c2);
		c2.gridx = 1;
		contractPanel.add(atimePara, c2);

		
		c2.gridx = 2;
		JLabel anoteLabel = new JLabel("合同提交项目经理-备注：", SwingConstants.RIGHT);
		JTextField anotePara = new JTextField(10);
		contractPanel.add(anoteLabel, c2);
		c2.gridx = 3;
		contractPanel.add(anotePara, c2);
		
		c2.gridy = 1;
		c2.gridx = 0;
		JLabel btimeLabel = new JLabel("甲方水印版合同返回-时间：", SwingConstants.RIGHT);
		JTextField btimePara = new JTextField(10);
		contractPanel.add(btimeLabel, c2);
		c2.gridx = 1;
		contractPanel.add(btimePara, c2);

		
		c2.gridx = 2;
		JLabel bnoteLabel = new JLabel("甲方水印版合同返回-备注：", SwingConstants.RIGHT);
		JTextField bnotePara = new JTextField(10);
		contractPanel.add(bnoteLabel, c2);
		c2.gridx = 3;
		contractPanel.add(bnotePara, c2);
		
		c2.gridy = 2;
		c2.gridx = 0;
		JLabel ctimeLabel = new JLabel("提交给院市场部-时间：", SwingConstants.RIGHT);
		JTextField ctimePara = new JTextField(10);
		contractPanel.add(ctimeLabel, c2);
		c2.gridx = 1;
		contractPanel.add(ctimePara, c2);

		
		c2.gridx = 2;
		JLabel cnoteLabel = new JLabel("提交给院市场部-备注：", SwingConstants.RIGHT);
		JTextField cnotePara = new JTextField(10);
		contractPanel.add(cnoteLabel, c2);
		c2.gridx = 3;
		contractPanel.add(cnotePara, c2);
		
		c2.gridy = 3;
		c2.gridx = 0;
		JLabel dtimeLabel = new JLabel("院市场部返回纸质合同-时间：", SwingConstants.RIGHT);
		JTextField dtimePara = new JTextField(10);
		contractPanel.add(dtimeLabel, c2);
		c2.gridx = 1;
		contractPanel.add(dtimePara, c2);

		
		c2.gridx = 2;
		JLabel dnoteLabel = new JLabel("院市场部返回纸质合同-备注：", SwingConstants.RIGHT);
		JTextField dnotePara = new JTextField(10);
		contractPanel.add(dnoteLabel, c2);
		c2.gridx = 3;
		contractPanel.add(dnotePara, c2);
		
		c2.gridy = 4;
		c2.gridx = 0;
		JLabel etimeLabel = new JLabel("纸质合同提交项目经理-时间：", SwingConstants.RIGHT);
		JTextField etimePara = new JTextField(10);
		contractPanel.add(etimeLabel, c2);
		c2.gridx = 1;
		contractPanel.add(etimePara, c2);

		
		c2.gridx = 2;
		JLabel enoteLabel = new JLabel("纸质合同提交项目经理-备注：", SwingConstants.RIGHT);
		JTextField enotePara = new JTextField(10);
		contractPanel.add(enoteLabel, c2);
		c2.gridx = 3;
		contractPanel.add(enotePara, c2);
		
		
		c2.gridy = 5;
		c2.gridx = 0;
		JLabel ftimeLabel = new JLabel("正式版合同寄回院市场部-时间：", SwingConstants.RIGHT);
		JTextField ftimePara = new JTextField(10);
		contractPanel.add(ftimeLabel, c2);
		c2.gridx = 1;
		contractPanel.add(ftimePara, c2);

		
		c2.gridx = 2;
		JLabel fnoteLabel = new JLabel("正式版合同寄回院市场部-备注：", SwingConstants.RIGHT);
		JTextField fnotePara = new JTextField(10);
		contractPanel.add(fnoteLabel, c2);
		c2.gridx = 3;
		contractPanel.add(fnotePara, c2);
		
		c2.gridx = 0;
		c2.gridy = 6;
		contractInfoPanel.add(new JLabel(" "), c2);
		c2.gridy = 7;
		contractInfoPanel.add(new JLabel(" "), c2);
		
		c2.gridy = 8;
		contractInfoPanel.add(new JLabel(" "), c2);
		
		
		tc.gridx = 2;
		add(contractPanel, tc);
		
		//决算金额==============
		JPanel finalPanel = new JPanel();
		finalPanel.setBorder(new TitledBorder("决算金额-勘探设计费"));
		finalPanel.setLayout(new GridBagLayout());
		GridBagConstraints c3 = new GridBagConstraints();
		c3.fill = GridBagConstraints.BOTH;
		// c.fill = GridBagConstraints.REMAINDER;
		c3.weightx = 1.0;
		c3.weighty = 1.0;
		
		c3.gridy = 0;
		c3.gridx = 0;
		JLabel fctimeLabel = new JLabel("决算完成时间：", SwingConstants.RIGHT);
		JTextField fctimePara = new JTextField(10);
		finalPanel.add(fctimeLabel, c3);
		c3.gridx = 1;
		finalPanel.add(fctimePara, c3);

		
		c3.gridx = 2;
		JLabel fwLabel = new JLabel("无线部分：", SwingConstants.RIGHT);
		JTextField fwPara = new JTextField(10);
		finalPanel.add(fwLabel, c3);
		c3.gridx = 3;
		finalPanel.add(fwPara, c3);
		
		c3.gridy = 1;
		c3.gridx = 0;
		JLabel fcLabel = new JLabel("传输部分：", SwingConstants.RIGHT);
		JTextField fcPara = new JTextField(10);
		finalPanel.add(fcLabel, c3);
		c3.gridx = 1;
		finalPanel.add(fcPara, c3);

		
		c3.gridx = 2;
		JLabel fpLabel = new JLabel("电源部分：", SwingConstants.RIGHT);
		JTextField fpPara = new JTextField(10);
		finalPanel.add(fpLabel, c3);
		c3.gridx = 3;
		finalPanel.add(fpPara, c3);
		
		c3.gridy = 2;
		c3.gridx = 0;
		JLabel fcivilLabel = new JLabel("土建部分：", SwingConstants.RIGHT);
		JTextField fcivilPara = new JTextField(10);
		finalPanel.add(fcivilLabel, c3);
		c3.gridx = 1;
		finalPanel.add(fcivilPara, c3);

		
		c3.gridx = 2;
		JLabel ftotalLabel = new JLabel("合计：", SwingConstants.RIGHT);
		JTextField ftotalPara = new JTextField(10);
		finalPanel.add(ftotalLabel, c3);
		c3.gridx = 3;
		finalPanel.add(ftotalPara, c3);
		
		tc.gridy = 1;
		tc.gridx = 0;
		add(finalPanel, tc);
		
		//发票交互情况一览表============
		JPanel fapiaoPanel = new JPanel();
		fapiaoPanel.setBorder(new TitledBorder("发票交互情况一览表"));
		fapiaoPanel.setLayout(new GridBagLayout());
		GridBagConstraints c4 = new GridBagConstraints();
		c4.fill = GridBagConstraints.BOTH;
		// c.fill = GridBagConstraints.REMAINDER;
		c4.weightx = 1.0;
		c4.weighty = 1.0;
		
		c4.gridy = 0;
		c4.gridx = 0;
		JLabel fatimeLabel = new JLabel("第一批-时间：", SwingConstants.RIGHT);
		JTextField fatimePara = new JTextField(10);
		fapiaoPanel.add(fatimeLabel, c4);
		c4.gridx = 1;
		fapiaoPanel.add(fatimePara, c4);

		
		c4.gridx = 2;
		JLabel fascaleLabel = new JLabel("第一批-金额比例：", SwingConstants.RIGHT);
		JTextField fascalePara = new JTextField(10);
		fapiaoPanel.add(fascaleLabel, c4);
		c4.gridx = 3;
		fapiaoPanel.add(fascalePara, c4);
		
		c4.gridx = 4;
		JLabel famoneyLabel = new JLabel("第一批-金额：", SwingConstants.RIGHT);
		JTextField famoneyPara = new JTextField(10);
		fapiaoPanel.add(famoneyLabel, c4);
		c4.gridx = 5;
		fapiaoPanel.add(famoneyPara, c4);
		
		c4.gridy = 1;
		c4.gridx = 0;
		JLabel fbtimeLabel = new JLabel("第二批-时间：", SwingConstants.RIGHT);
		JTextField fbtimePara = new JTextField(10);
		fapiaoPanel.add(fbtimeLabel, c4);
		c4.gridx = 1;
		fapiaoPanel.add(fbtimePara, c4);

		
		c4.gridx = 2;
		JLabel fbscaleLabel = new JLabel("第二批-金额比例：", SwingConstants.RIGHT);
		JTextField fbscalePara = new JTextField(10);
		fapiaoPanel.add(fbscaleLabel, c4);
		c4.gridx = 3;
		fapiaoPanel.add(fbscalePara, c4);
		
		c4.gridx = 4;
		JLabel fbmoneyLabel = new JLabel("第二批-金额：", SwingConstants.RIGHT);
		JTextField fbmoneyPara = new JTextField(10);
		fapiaoPanel.add(fbmoneyLabel, c4);
		c4.gridx = 5;
		fapiaoPanel.add(fbmoneyPara, c4);
		
		
		c4.gridy = 2;
		c4.gridx = 0;
		JLabel fctLabel = new JLabel("第三批-时间：", SwingConstants.RIGHT);
		JTextField fctPara = new JTextField(10);
		fapiaoPanel.add(fctLabel, c4);
		c4.gridx = 1;
		fapiaoPanel.add(fctPara, c4);

		
		c4.gridx = 2;
		JLabel fcscaleLabel = new JLabel("第三批-金额比例：", SwingConstants.RIGHT);
		JTextField fcscalePara = new JTextField(10);
		fapiaoPanel.add(fcscaleLabel, c4);
		c4.gridx = 3;
		fapiaoPanel.add(fcscalePara, c4);
		
		c4.gridx = 4;
		JLabel fcmoneyLabel = new JLabel("第三批-金额：", SwingConstants.RIGHT);
		JTextField fcmoneyPara = new JTextField(10);
		fapiaoPanel.add(fcmoneyLabel, c4);
		c4.gridx = 5;
		fapiaoPanel.add(fcmoneyPara, c4);
		
		tc.gridx = 1;
		add(fapiaoPanel,tc);
		
		//工程验收情况和备注==========
		JPanel overlPanel = new JPanel();
		overlPanel.setBorder(new TitledBorder("发票交互情况一览表"));
		overlPanel.setLayout(new GridBagLayout());
		GridBagConstraints c5 = new GridBagConstraints();
		c5.fill = GridBagConstraints.BOTH;
		// c.fill = GridBagConstraints.REMAINDER;
		c5.weightx = 1.0;
		c5.weighty = 1.0;
		
		c5.gridy = 0;
		c5.gridx = 0;
		JLabel projectAcceptLabel = new JLabel("工程验收情况：", SwingConstants.RIGHT);
		JTextField projectAcceptPara = new JTextField(10);
		overlPanel.add(projectAcceptLabel, c5);
		c5.gridx = 1;
		overlPanel.add(projectAcceptPara, c5);

		
		c5.gridx = 2;
		JLabel projectNoteLabel = new JLabel("备注：", SwingConstants.RIGHT);
		JTextField projectNotePara = new JTextField(10);
		overlPanel.add(projectNoteLabel, c5);
		c5.gridx = 3;
		overlPanel.add(projectNotePara, c5);
		
		tc.gridx = 2;
		add(overlPanel, tc);
		
		
		JPanel btnPanel = new JPanel();
		JButton selectBtn = new JButton("查询...");
		JButton resetBtn = new JButton("重置...");
		btnPanel.add(selectBtn);
		btnPanel.add(resetBtn);
		tc.fill = GridBagConstraints.CENTER;
		tc.gridy = 2;
		tc.gridx = 1;
		add(btnPanel, tc);
		
	}

}
