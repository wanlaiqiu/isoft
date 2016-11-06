package com.van.framework.filter.xss.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


/** 
 * @className: WhiteListFileUtil.java<br/>
 * @classDescription: Description here<br/>
 * @createTime: 2015年5月15日
 * @author Van
 */
public class WhiteListFileUtil {

	private static final String WHITE_LIST_EXCEL_PATH = "whiteList.xls";
	
	private static final String WHITE_LIST_XML_PATH = "whiteList.xml";
	
	public static final String KEY_SPLIT = "!#!";
	
	public static String ROOT_PATH = null;
	
	
	
	public static ConcurrentMap<String, String> initWhiteList(){
		String rootPath = getRootPath();
		String xmlPath = rootPath + WHITE_LIST_XML_PATH;
		File xmlFile = new File(xmlPath);
		if(xmlFile.exists()){
			return initWhiteListFromXml(xmlFile);
		}
		
		String excelPath = rootPath + WHITE_LIST_EXCEL_PATH;
		File excelFile = new File(excelPath);
		if(!excelFile.exists()){
			excelPath = rootPath + "whiteList.xls";
		}
		xmlPath = excelPath.replace(".xls", ".xml");
		xmlFile = new File(xmlPath);
		ConcurrentMap<String, String> whiteList = new ConcurrentHashMap<String, String>();
		if(xmlFile.exists()){
			whiteList = initWhiteListFromXml(xmlFile);
		} else{
			whiteList = initWhiteListFromExcel(excelPath);
			writeToXml(whiteList, xmlPath);
					
		}
		return whiteList;
		
	}
	
	@SuppressWarnings("unchecked")
	public static ConcurrentMap<String, String> initWhiteListFromXml(File xmlFile){
		SAXReader saxReader = new SAXReader();
		ConcurrentMap<String, String> whiteList = null;
		try {
			whiteList = new ConcurrentHashMap<String, String>();
			Document document = saxReader.read(xmlFile);
			Element root = document.getRootElement();
			for(Iterator<Element> it = root.elementIterator(); it.hasNext();){
				Element item = it.next();
				String url = item.attributeValue("url"),
					   param = item.attributeValue("param"),
					   type = item.attributeValue("type");
				whiteList.put(url + KEY_SPLIT + param, type);
				
			}
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		}
		return whiteList;
		
	}
	
	public static ConcurrentMap<String, String> initWhiteListFromExcel(String excelPath){
		ConcurrentMap<String, String> whiteList = null;
		File excelFile = new File(excelPath);
		if(excelFile.exists()){
			whiteList = new ConcurrentHashMap<String, String>();
			Workbook work = null;
			Sheet sheet = null;
			try {
				work = Workbook.getWorkbook(excelFile);
				sheet = work.getSheet(0);
				int rows = sheet.getRows();
				for(int i = 1; i < rows; i++){
					String url = sheet.getCell(0,i).getContents(),
						   param = sheet.getCell(1, i).getContents(),
						   type = sheet.getCell(2, i).getContents();
					whiteList.put(url + KEY_SPLIT + param, type);
				}
				
			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				if(null != work)
					work.close();
			}
		}
		return whiteList;
	}
	
	private static void writeToXml(ConcurrentMap<String, String> whiteList, String xmlPath){
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("root");
		if(null != whiteList){
			Set<Entry<String, String>> entrySet = whiteList.entrySet();
			for(Entry<String, String> entry : entrySet){
				String key = entry.getKey();
				Element item = root.addElement("item");
				String[] splitStr = key.split(KEY_SPLIT);
				String param = null;
				if(splitStr.length > 1){
					param = splitStr[1];
				}
				String url = splitStr[0];
				item.addAttribute("url", url);
				item.addAttribute("param", param);
				item.addAttribute("type", entry.getValue());
			}
		}
		
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			XMLWriter writer = new XMLWriter(new FileOutputStream(xmlPath), format);
			writer.write(document);
			writer.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	public static String getRootPath(){
		if(null != ROOT_PATH){
			return ROOT_PATH;
		}
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if(null == classLoader){
			classLoader = ClassLoader.getSystemClassLoader();
		}
		URL url = classLoader.getResource("/");
		ROOT_PATH = new File(new File(url.getPath() + "/").getParent() + "/").getParent() + "/";
		
		return ROOT_PATH;
	}
	
	
}
