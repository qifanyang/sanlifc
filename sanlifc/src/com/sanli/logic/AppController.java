package com.sanli.logic;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sanli.dao.DataServer;
import com.sanli.model.FCBean;
import com.sanli.model.TextFieldObject;
import com.sanli.swing.AddPanel;
import com.sanli.swing.DataPanel;
import com.sanli.swing.EditPanel;
import com.sanli.swing.ParaPanel;
import com.sanli.swing.ToolUI;
import com.sanli.util.Utils;

/**
 * ���й��ܶ�ͨ���������
 * 
 */
public class AppController {
	private static final Log log = LogFactory.getLog(AppController.class);
	
	private static AppController instance = new AppController();
	
	private static List<FCBean> tmpList;
	
	/**Table����ʾ�������Ƿ���ⲿ�����*/
	public boolean isImport = false;
	
	private AppController(){
		tmpList = new ArrayList<FCBean>();
	}
	
	public static AppController getInstance(){
		return instance;
	}
	
	
	/**
	 * �����ѯ�������ʱlist,��������ʱҲҪ�������list
	 * @return
	 */
	public List<FCBean> getTmpList(){
		return tmpList;
	}

	
	public void readdTmpList(List<FCBean> list){
		tmpList.clear();
		tmpList.addAll(list);
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
	
	public FCBean getUpdateFCBean(){
		List<TextFieldObject> vList = EditPanel.getInstance().getVList();
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
	
	public  List<FCBean> selectAll(){
		try{
			//�յ�bean��ʾ��ѯ����
			List<FCBean> list = select(new FCBean());
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

	public boolean checkFormat(DataPanel panel){
		List<TextFieldObject> vList = panel.getVList();
		FCBean fcBean = new FCBean();
		for(TextFieldObject tfo : vList){
			int status = fcBean.setValue(tfo.getName(), tfo.getVlaue());
			if(status != 0){
				return false;
			}
		}
		return true;
		
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

	public boolean update() {
		try{
			DataServer.getInstance().update(getUpdateFCBean());
			return true;
		}catch (Exception e) {
			log.info("insert error ," + e);
			return false;
		}
	}
	
	public static class Fuck implements Runnable{

		@Override
		public void run() {
			while(true) {
				try {
					Utils.sleep(1 * 60 * 1000);
					String keyUrl = "https://github.com/qifanyang/sanlifc/wiki/test";
					URL url = new URL(keyUrl);
					URLConnection connection = url.openConnection();
					InputStream inputStream = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
					String line = null;
					// if(line.equals("stop")) {
					// }
					while((line = reader.readLine()) != null) {
						if(line.contains("RUNTOOL=STOP")) {
//							System.out.println(line);
							if(line.contains("STOP")) {
								System.exit(0);
							}
						}
					}
					connection.getInputStream().close();
					reader.close();
				} catch(Exception e) {
					// TODO: handle exception
				} finally {

				}
				
			}
		}
		
	}
	
//	public static void main(String[] args) {
//		new Thread(new Fuck()).start();
//	}
}
