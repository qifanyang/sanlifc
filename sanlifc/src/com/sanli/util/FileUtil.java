package com.sanli.util;

import java.io.FileInputStream;
import java.io.InputStream;


public class FileUtil{
	
	private static FileUtil instance = new FileUtil();
	
	public FileUtil getInstance(){
		return instance;
	}
	
	public static InputStream getAssetByClassLoader(String name){
		return instance.getClass().getClassLoader().getResourceAsStream("com/sanli/data/" + name);
	}

//	public static InputStream getAssetByFilePath(String path){
//		return new FileInputStream(path);
//	}
}
