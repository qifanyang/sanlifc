package com.sanli.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sanli.model.FCBean;

/**
 *执行数据库操作
 */
public class DataServer {
	
	
	private static DataServer instance = new DataServer();
	
	private SqlSessionFactory sqlSessionFactory;
	
	
	
	private DataServer(){
		String resource = "com/sanli/data/mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	public static DataServer getInstance(){
		return instance;
	}
	
	public void insert(){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			FCBean fcBean = new FCBean();
			fcBean.city = "成都222";
			fcBean.id = 1;
			int i = session.insert("com.sanli.data.FLowChartMapper.insert", fcBean);
			System.out.println("inset num = " + i);
			session.commit();
		} finally {
			session.close();
		}
	}
	
	private void delete() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			FCBean bean = new FCBean();
//			bean.name = "updateTest";
			bean.uuid = 2;
			int i = session.update("com.sanli.data.FLowChartMapper.delete", bean);
			System.out.println("delete num = " + i);
			session.commit();
		} finally {
			session.close();
		}
	}

	private void update() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			FCBean fcBean = new FCBean();
			fcBean.city = "成都更新";
			fcBean.id = 0;
			int i = session.update("com.sanli.data.FLowChartMapper.update", fcBean);
			System.out.println("update num = " + i);
			session.commit();
		} finally {
			session.close();
		}
	}

	public List<FCBean> select(FCBean bean){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			
//			bean = new FCBean();
//			bean.id = 0;
			List<FCBean> list = session.selectList("com.sanli.data.FLowChartMapper.select", bean);
//			FCBean list = (FCBean)session.selectOne("com.sanli.data.FLowChartMapper.select", bean);
			if(list == null || list.size() == 0){
				System.out.println("selcect null .........");
				return null;
			}
			System.out.println(list.get(0).id + " : " + list.get(0).city);
			return list;
		} finally {
			session.close();
		}
	}

	public static void main(String[] args) {
		DataServer dataServer = new DataServer();
		dataServer.insert();
//		dataServer.select(new FCBean());
//		dataServer.update();
//		dataServer.delete();
	}

}
