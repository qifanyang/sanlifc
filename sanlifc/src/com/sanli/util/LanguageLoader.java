package com.sanli.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ����[key,value]��ֵ��,���ڹ��ʻ�
 * @author XF
 */
public class LanguageLoader{
	private final static Log log = LogFactory.getLog(LanguageLoader.class);
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
				if(strings.length != 2){
					log.error("key=value���ô��� , str = " + line);
				}
				map.put(strings[0].trim(), strings[1].trim());
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
