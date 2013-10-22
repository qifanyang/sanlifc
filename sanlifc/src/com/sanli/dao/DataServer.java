package com.sanli.dao;

import java.io.IOException;
import java.io.InputStream;

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
			fcBean.city = "成都qqq";
			int i = session.insert("com.sanli.dao.FLowChartMapper.insert", fcBean);
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
			int i = session.update("com.sanli.dao.FLowChartMapper.delete", bean);
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
			int i = session.update("com.sanli.dao.FLowChartMapper.update", fcBean);
			System.out.println("update num = " + i);
			session.commit();
		} finally {
			session.close();
		}
	}

	public void select(){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			
			FCBean bean = new FCBean();
			bean.id = 1;
			
			FCBean fcBean = (FCBean) session.selectOne("com.sanli.dao.FLowChartMapper.select", bean);

			if(fcBean == null){
				System.out.println("null .........");
				return;
			}
			System.out.println(fcBean.id + " : " + fcBean.city);

		} finally {
			session.close();
		}
	}

	public static void main(String[] args) {
		DataServer dataServer = new DataServer();
//		dataServer.select();
//		dataServer.insert();
//		dataServer.update();
//		dataServer.delete();
	}

}
