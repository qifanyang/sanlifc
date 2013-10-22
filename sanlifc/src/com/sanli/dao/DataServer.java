package com.sanli.dao;

/**
 *执行数据库操作
 */
public class DataServer {
	
	private static DataServer instance = new DataServer();
	
	private DataServer(){}
	
	public static DataServer getInstance(){
		return instance;
	}
	
	public void select(){
		
	}
	
	public void insert(){
		
	}

}
