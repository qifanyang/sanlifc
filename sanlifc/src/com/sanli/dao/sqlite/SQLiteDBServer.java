package com.sanli.dao.sqlite;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import com.sanli.dao.mysql.DataServer;

/**
 * 
 *SQLite数据库,增,删,改,查
 */
public class SQLiteDBServer extends DataServer{
	
	public static void main(String[] args) {
		SQLiteDBServer dbServer = new SQLiteDBServer();
		SqlSession session = dbServer.sqlSessionFactory.openSession();
		boolean ex = false;
		try {
			DatabaseMetaData metaData = session.getConnection().getMetaData();
			ResultSet tables = metaData.getTables(null, null, "flowchart", null);
			if(tables.next()){
				System.out.println("yes");
				ex = true;
			}else {
				System.out.println("NO");
			}
			tables.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(ex){
			session.delete("com.sanli.data.FLowChartMapper.dt");
			session.commit();
			System.out.println("删除表");
		}else {
			session.update("com.sanli.data.FLowChartMapper.create");
			session.commit();
			System.out.println("创建表");
		}
	}

}
