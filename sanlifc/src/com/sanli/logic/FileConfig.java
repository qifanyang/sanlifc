package com.sanli.logic;


/**
 * @author XF
 */
public class FileConfig {

	private static String root = "";
	private static String separator = "/";
	
	public static String getRoot(){
		if(root.length() == 0){
			String rp = FileConfig.class.getClassLoader().getResource(".").getFile();
			int endIndex = rp.lastIndexOf("/bin/");
			root = rp.substring(1, endIndex);
		}
		return root;
	}
	
	public static void setRoot(String root){
		FileConfig.root = root;
	}
	public static String getDesignRoot(){
		return getRoot() + separator + "design" + separator;
	}
	
	
	
	public static void main(String[] args) {
		
//		System.out.println(getDesignRoot());
//		String path = FileConfig.class.getClassLoader().getResource(".").getFile();
//		int index = path.lastIndexOf("bin/");
//		System.out.println(path.substring(1, index));
//		
//		System.out.println(System.getProperty("user.dir"));
		System.out.println(getDesignRoot());
	}
}
