package com.sanli.swing;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sanli.dao.mysql.DataServer;
import com.sanli.logic.AppController;
import com.sanli.model.FCBean;

/**
 * 查询面板，用于显示查询结果
 * 
 *TODO 这个类里面的方法有点乱,后面要整理,有的方法要分开,不然很多逻辑都在一个方法里面.不放便复用
 * 
 * @author XF 2013-10-21 下午10:19:47
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
	 * 采用JTable显示
	 * @type 1:单个     2:所有
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
	public void doEdit(int id) {
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
	public void doDelete(int id) {
		
		boolean isDelete = AppController.getInstance().deleteOne(id);
		if(isDelete){
			AppWinUtils.showNormalMsg("删除[成功]!");
		}else{
			AppWinUtils.showWarnMsg("删除[失败]!");
		}
		
		List<FCBean> list = DataServer.getInstance().select(new FCBean());
		showInTable(list);
	}

}
