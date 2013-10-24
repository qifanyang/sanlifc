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
 * 所有功能都通过该类完成
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
	 * 保存查询结果的临时list
	 * @return
	 */
	public List<FCBean> getTmpList(){
		return tmpList;
	}

	/**
	 * 从数据库查询数据时,从添加数据面板读取数据填充Bean
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
	 * 向数据库插入数据时,从添加数据面板读取数据填充Bean
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
			//从数据库查询出来的数据,时间为long,table显示需要转换为yyyy-MM-dd
			//这里可以使用JTable的render
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
	 * 检查是否可以插入,返回null表示可以插入
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
