package com.sanli.logic;

import java.util.List;

import com.sanli.dao.DataServer;
import com.sanli.model.FCBean;
import com.sanli.swing.ParaPanel;

/**
 * 完成交互功能
 */
public class Controller {
	
	
	private static Controller instance = new Controller();
	
	private Controller(){}
	
	public static Controller getInstance(){
		return instance;
	}
	
	
	public FCBean getFCBean(){
		List<TextFieldObject> vList = ParaPanel.getInstance().getVList();
		FCBean fcBean = new FCBean();
		for(TextFieldObject tfo : vList){
			fcBean.setValue(tfo.getName(), tfo.getVlaue());
		}
		return fcBean;
	}
	
	public List<FCBean> select(){
		FCBean bean = getFCBean();
		//TODO maybe do some check.....
		List<FCBean> list = DataServer.getInstance().select(bean);
		return list;
	}

}
