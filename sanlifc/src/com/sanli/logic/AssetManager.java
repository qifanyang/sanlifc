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
 * �ı��ļ��ļ��غ͵���
 *
 * @author XF
 * 2013-10-22 ����10:59:01
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
		//��ȡģ��
		// ������Excel�������ļ�������
		   HSSFWorkbook workbook = new HSSFWorkbook(FileUtil.getAssetByClassLoader("moban.xls"));
		   // �����Թ���������á�
		   // �����ǰ������ã������Ǽٶ����ű�����ȱʡ��"Sheet1"��
		   HSSFSheet sheet = workbook.getSheet("2013���");
		   // Ҳ����getSheetAt(int index)���������ã�
		   // ��Excel�ĵ��У���һ�Ź������ȱʡ������0��
		   // �����Ϊ��HSSFSheet sheet = workbook.getSheetAt(0);
		   // ��ȡ���϶˵�Ԫ
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
		   // �����Ԫ���ݣ�cell.getStringCellValue()����ȡ���ڵ�Ԫ��ֵ
//		   System.out.println("���϶˵�Ԫ�ǣ� " + cell.getStringCellValue());
		   return true;
		
	}
	
	
	public static void main(String[] args) throws Exception {
		FCBean fcBean = new FCBean();
		fcBean.id = 1;
		fcBean.city = "nihao";
		fcBean.year = 2013;
		fcBean.check_info = "�������";
		fcBean.remark = "��עһ";
		FCBean fcBean1 = new FCBean();
		fcBean1.id = 1;
		fcBean1.city = "�ɶ�";
		fcBean1.year = 2013;
		fcBean1.check_info = "�������222";
		fcBean1.remark = "��עһ";
		
		
		ArrayList<FCBean> list = new ArrayList<FCBean>();
		list.add(fcBean);
		list.add(fcBean1);
		
//		getInstance().exportTxt(null, list);
		
//		exportExcel("D:/tt.xls", list);
	}
	
	
}
