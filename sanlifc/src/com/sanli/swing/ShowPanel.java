package com.sanli.swing;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sanli.dao.mysql.DataServer;
import com.sanli.logic.AppController;
import com.sanli.model.FCBean;

/**
 * ��ѯ��壬������ʾ��ѯ���
 * 
 *TODO ���������ķ����е���,����Ҫ����,�еķ���Ҫ�ֿ�,��Ȼ�ܶ��߼�����һ����������.���ű㸴��
 * 
 * @author XF 2013-10-21 ����10:19:47
 */
public class ShowPanel extends TablePanel{
	private final static Log log = LogFactory.getLog(ShowPanel.class);
	private static final long serialVersionUID = 1L;
	private static ShowPanel instance = new ShowPanel();
	
	private ShowPanel() {
		super();
	}

	public static ShowPanel getInstance() {
		return instance;
	}
	
	/**
	 * ����JTable��ʾ
	 * @type 1:����     2:����
	 * @throws IllegalAccessException
	 * @throws Exception
	 */
	public void showSelectResult(int type1) {
		log.error("show result in JTable....");
		List<FCBean> list = null;
		if(type1 == 1){
			list = AppController.getInstance().select();
		}else if(type1 == 2){
			list = AppController.getInstance().selectAll();
		}
		
		showInTable(list);
	}

	@Override
	public void doEdit(int id, int row) {
		FCBean bean = null;
		for(FCBean bb : beanList){
			if(bb.id == id){
				bean = bb;
				break;
			}
		}
		EditDialog.getInstance().showEditDialog(bean);
	}

	@Override
	public void doDelete(int id, int row) {
		
		boolean isDelete = AppController.getInstance().deleteOne(id);
		if(isDelete){
			AppWinUtils.showNormalMsg("ɾ��[�ɹ�]!");
		}else{
			AppWinUtils.showWarnMsg("ɾ��[ʧ��]!");
		}
		
		List<FCBean> list = DataServer.getInstance().select(new FCBean());
		showInTable(list);
	}

}
