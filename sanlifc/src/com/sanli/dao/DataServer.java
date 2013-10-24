package com.sanli.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sanli.model.FCBean;

/**
 *执行数据库操作
 */
public class DataServer {
	private static final Log log = LogFactory.getLog(DataServer.class);
	
	private static DataServer instance = new DataServer();
	
	private SqlSessionFactory sqlSessionFactory;
	
	
	
	private DataServer(){
		String resource = "com/sanli/data/mybatis-config.xml";
		log.info("load myBatis config , resource path = " + resource);
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
	
	public void insert(FCBean bean){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			int i = session.insert("com.sanli.data.FLowChartMapper.insert", bean);
			log.info("insert bean number = " + i);
			session.commit();
		} finally {
			session.close();
		}
	}
	
	public void delete(int id) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			int i = session.update("com.sanli.data.FLowChartMapper.delete", id);
			log.info("delete bean number = " + i);
			session.commit();
		} finally {
			session.close();
		}
	}

	public void update(FCBean bean) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			session.update("com.sanli.data.FLowChartMapper.update", bean);
			log.info("update bean uuid = " + bean.uuid);
			session.commit();
		} finally {
			session.close();
		}
	}

	public List<FCBean> select(FCBean bean){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			
			List<FCBean> list = session.selectList("com.sanli.data.FLowChartMapper.select", bean);
			return list;
		} finally {
			session.close();
		}
	}
	
	public FCBean selectOne(FCBean bean){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			
			FCBean one = (FCBean)session.selectOne("com.sanli.data.FLowChartMapper.selectOne", bean.id);
			return one;
		} finally {
			session.close();
		}
		
	}
	public FCBean selectOne(int id){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			
			FCBean one = (FCBean)session.selectOne("com.sanli.data.FLowChartMapper.selectOne", id);
			return one;
		} finally {
			session.close();
		}
		
	}

//	public static void main(String[] args) {
//		DataServer dataServer = new DataServer();
//		dataServer.insert();
//		dataServer.select(new FCBean());
//		dataServer.update();
//		dataServer.delete();
//	}

}
