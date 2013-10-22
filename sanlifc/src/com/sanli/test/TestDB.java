package com.sanli.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.prefs.BackingStoreException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sanli.model.Bean;

public class TestDB {
	private SqlSessionFactory sqlSessionFactory;

	private void init() {
		String resource = "com/sanli/dao/mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	public void select() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Bean bean = (Bean) session.selectOne("com.sanli.dao.FCMapper.select", 1);

			System.out.println(bean.id + " : " + bean.name);

		} finally {
			session.close();
		}

	}

	public void insert() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Bean bean = new Bean();
			bean.name = "insertTest";
			int i = session.insert("com.sanli.dao.FCMapper.insert", bean);
			System.out.println("inset num = " + i);
			session.commit();
		} finally {
			session.close();
		}

	}
	
	public void update(){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Bean bean = new Bean();
//			bean.name = "1212";
			bean.id = 3;
			int i = session.update("com.sanli.dao.FCMapper.update", bean);
			System.out.println("update num = " + i);
			session.commit();
		} finally {
			session.close();
		}
		
	}
	
	public void delete(){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Bean bean = new Bean();
//			bean.name = "updateTest";
			bean.id = 6;
			int i = session.update("com.sanli.dao.FCMapper.delete", bean);
			System.out.println("delete num = " + i);
			session.commit();
		} finally {
			session.close();
		}
		
	}

	public static void main(String[] args) throws IOException {

		TestDB testDB = new TestDB();
		testDB.init();
		testDB.select();
		testDB.insert();
		testDB.update();
		testDB.delete();

	}

}
