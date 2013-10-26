package com.sanli.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 加载[key,value]键值对,用于国际化
 * @author XF
 */
public class LanguageLoader{
	
	private static LanguageLoader instance = new LanguageLoader();
	private static Map<String, String> map = new HashMap<String, String>();
	
	private LanguageLoader(){}
	
	
	public void init(){
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("com/sanli/data/ui_name.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		try {
			while((line = reader.readLine()) != null){
				if(line.startsWith("#"))continue;
				
				String[] strings = line.split("=");
				map.put(strings[0], strings[1] );
			}
		} catch(IOException e) {
			e.printStackTrace();
		}finally{
			try {
				inputStream.close();
				reader.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static LanguageLoader getInstance(){
		return instance;
	}

	public String getUIName(String key){
		return "[" + map.get(key) + "]";
		
	}
	
	public String[] getUINameList(){
		String[] ss = new String[map.size()];
		map.values().toArray(ss);
		return ss;
	}
}
