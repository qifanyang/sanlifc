package com.sanli.logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sanli.dao.DataServer;
import com.sanli.model.FCBean;
import com.sanli.swing.AddPanel;
import com.sanli.swing.ParaPanel;

/**
 * ���й��ܶ�ͨ���������
 * 
 */
public class AppController {
	private static final Log log = LogFactory.getLog(AppController.class);
	
	private static AppController instance = new AppController();
	
	private static List<FCBean> tmpList;
	
	private AppController(){
		tmpList = new ArrayList<FCBean>();
	}
	
	public static AppController getInstance(){
		return instance;
	}
	
	
	/**
	 * �����ѯ�������ʱlist
	 * @return
	 */
	public List<FCBean> getTmpList(){
		return tmpList;
	}

	/**
	 * �����ݿ��ѯ����ʱ,�������������ȡ�������Bean
	 * @return
	 */
	public FCBean getSelectFCBean(){
		List<TextFieldObject> vList = ParaPanel.getInstance().getVList();
		FCBean fcBean = new FCBean();
		for(TextFieldObject tfo : vList){
			fcBean.setValue(tfo.getName(), tfo.getVlaue());
		}
		return fcBean;
	}
	
	/**
	 * �����ݿ��������ʱ,�������������ȡ�������Bean
	 * @return
	 */
	public FCBean getInsertFCBean(){
		List<TextFieldObject> vList = AddPanel.getInstance().getVList();
		FCBean fcBean = new FCBean();
		for(TextFieldObject tfo : vList){
			fcBean.setValue(tfo.getName(), tfo.getVlaue());
		}
		return fcBean;
	}
	
	public List<FCBean> select(){
		try{
			FCBean bean = getSelectFCBean();
			List<FCBean> list = select(bean);
			//�����ݿ��ѯ����������,ʱ��Ϊlong,table��ʾ��Ҫת��Ϊyyyy-MM-dd
			//�������ʹ��JTable��render
			tmpList.clear();
			tmpList.addAll(list);
			return list;
		}catch (Exception e) {
			log.info("select error ," + e);
		}
		return null;
	}
	
	public List<FCBean> select(FCBean bean){
		List<FCBean> list = DataServer.getInstance().select(bean);
		return list;
	}
	
//	public
	/**
	 * ����Ƿ���Բ���,����null��ʾ���Բ���
	 * @return
	 */
	public FCBean checkInsertAble(){
		try{
			FCBean bean = getInsertFCBean();
			FCBean one = DataServer.getInstance().selectOne(bean);
			return one;
//			return false;
		}catch (Exception e) {
			log.info("checkInsertAble error ," + e);
			return new FCBean();
		}
	}
	
	
	public FCBean checkDeleAble(int id) {
		return DataServer.getInstance().selectOne(id);
	}

	public boolean insert(){
		try{
			FCBean bean = getInsertFCBean();
	
			DataServer.getInstance().insert(bean);
			return true;
		}catch (Exception e) {
			log.info("insert error ," + e);
			return false;
		}
		
	}

	public boolean deleteOne(int id) {
		try{
			DataServer.getInstance().delete(id);
			return true;
		}catch (Exception e) {
			log.info("insert error ," + e);
			return false;
		}
	}
}
