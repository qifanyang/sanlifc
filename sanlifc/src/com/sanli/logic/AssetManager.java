package com.sanli.logic;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.sanli.model.FCBean;
import com.sanli.util.FileUtil;
import com.sanli.util.Utils;

/**
 * 文本文件的加载和导出
 *
 * @author XF
 * 2013-10-22 下午10:59:01
 */
public class AssetManager {
	
	private static AssetManager instance = new AssetManager();
	
	public static AssetManager getInstance(){
		return instance;
	}
	
	public  boolean exportTxt(String path, List<FCBean> list) throws Exception{
		StringBuilder builder = new StringBuilder();
		for(FCBean bean : list){
			Field[] fields = bean.getClass().getFields();
			for(Field f : fields){
				if(!f.getName().equalsIgnoreCase("uuid")){
					Class<?> type = f.getType();
					if(type == int.class){
						builder.append(f.getInt(bean)).append("\t");
					}else if(type == long.class){
						builder.append(Utils.millisecondToDate(f.getLong(bean))).append("\t");
					}else if(type == float.class){
						builder.append(f.getFloat(bean)).append("\t");
					}else if(type == String.class){
						Object obj = f.get(bean);
						if(obj == null){
							builder.append("\t");
						}else{
							builder.append(f.get(bean)).append("\t");
						}
					}
				}
			}
			builder.append("\n");
		}
		//
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
		writer.write(builder.toString());
		writer.flush();
		writer.close();
		System.out.println(builder.toString());
		return true;
	}
	
	
	public boolean exportExcel(String path, List<FCBean> list) throws Exception{
		//读取模版
		// 创建对Excel工作簿文件的引用
		   HSSFWorkbook workbook = new HSSFWorkbook(FileUtil.getAssetByClassLoader("moban.xls"));
		   // 创建对工作表的引用。
		   // 本例是按名引用（让我们假定那张表有着缺省名"Sheet1"）
		   HSSFSheet sheet = workbook.getSheet("2013年度");
		   // 也可用getSheetAt(int index)按索引引用，
		   // 在Excel文档中，第一张工作表的缺省索引是0，
		   // 其语句为：HSSFSheet sheet = workbook.getSheetAt(0);
		   // 读取左上端单元
		   HSSFRow row = sheet.getRow(0);
		   HSSFCell cell = row.getCell(0);
		   int y = 3;
		   for(FCBean bean : list){
			   int x = 0;
			   row = sheet.getRow(y);
			   Field[] fields = bean.getClass().getFields();
				for(Field f : fields) {
					if(!f.getName().equalsIgnoreCase("uuid")) {
						HSSFCell cc = row.getCell(x);
						Class<?> type = f.getType();
						if(type == int.class) {
							String value = String.valueOf(f.getInt(bean) <= 0 ? "" : f.getInt(bean));
							cc.setCellValue(value);
						} else if(type == long.class) {
							cc.setCellValue(Utils.millisecondToDate(f.getLong(bean)));
						} else if(type == float.class) {
							cc.setCellValue(String.valueOf(f.getFloat(bean) <= 0 ? "" : f.getFloat(bean)));
						} else if(type == String.class) {
							cc.setCellValue(String.valueOf(f.get(bean) == null ? "" : f.get(bean)));
						}
						x++;
					}
				}
				y++;
		   }
		   
		   // Write the output to a file
		   FileOutputStream fileOut = new FileOutputStream(path);
		   workbook.write(fileOut);
		   fileOut.close();
		   // 输出单元内容，cell.getStringCellValue()就是取所在单元的值
//		   System.out.println("左上端单元是： " + cell.getStringCellValue());
		   return true;
		
	}
	
	/**
	 * 导入数据的时候,没得名字标识了,只有按顺序来了,程序Bean变量顺序应该和excel文件中的一样
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public List<FCBean> importExcel(String path) throws Exception {
		List<FCBean> list = new ArrayList<FCBean>();
		// 读取批量导入文件
		// 创建对Excel工作簿文件的引用
		HSSFWorkbook workbook = new HSSFWorkbook(
				FileUtil.getAssetByClassLoader("moban.xls"));
		// 创建对工作表的引用。
		// 本例是按名引用（让我们假定那张表有着缺省名"Sheet1"）
		HSSFSheet sheet = workbook.getSheet("2013年度");
		// 也可用getSheetAt(int index)按索引引用，
		// 在Excel文档中，第一张工作表的缺省索引是0，
		// 其语句为：HSSFSheet sheet = workbook.getSheetAt(0);
		// 读取左上端单元
		int lastRowNum = sheet.getLastRowNum();
		int y = 3;
		for(; y < lastRowNum; y++){
			int x = 0;
			FCBean bean = new FCBean();
			HSSFRow row = sheet.getRow(y);
			Field[] fields = bean.getClass().getFields();
			for (Field f : fields) {
				if (!f.getName().equalsIgnoreCase("uuid")) {
					HSSFCell cc = row.getCell(x);
					bean.setValue(f.getName(), cc.getStringCellValue());
//					Class<?> type = f.getType();
//					if (type == int.class) {
//					} else if (type == long.class) {
//						cc.setCellValue(Utils.millisecondToDate(f.getLong(bean)));
//					} else if (type == float.class) {
//						cc.setCellValue(String.valueOf(f.getFloat(bean) <= 0 ? ""
//								: f.getFloat(bean)));
//					} else if (type == String.class) {
//						cc.setCellValue(String.valueOf(f.get(bean) == null ? ""
//								: f.get(bean)));
//					}
					x++;
				}
			}
			list.add(bean);
		}

		return list;

	}
	
	public static void main(String[] args) throws Exception {
		FCBean fcBean = new FCBean();
		fcBean.id = 1;
		fcBean.city = "nihao";
		fcBean.year = 2013;
		fcBean.check_info = "验收情况";
		fcBean.remark = "备注一";
		FCBean fcBean1 = new FCBean();
		fcBean1.id = 1;
		fcBean1.city = "成都";
		fcBean1.year = 2013;
		fcBean1.check_info = "验收情况222";
		fcBean1.remark = "备注一";
		
		
		ArrayList<FCBean> list = new ArrayList<FCBean>();
		list.add(fcBean);
		list.add(fcBean1);
		
//		getInstance().exportTxt(null, list);
		
//		exportExcel("D:/tt.xls", list);
	}
	
	
}
