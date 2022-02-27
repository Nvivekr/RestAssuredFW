package testutilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class FileReaderuserdef {
	
    public Sheet readExcel(String filePath,String fileName, String sheetName) throws IOException{
    //Create an object of File class to open xlsx file
    File file = new File(filePath+"\\"+fileName);

    //Create an object of FileInputStream class to read excel file
    FileInputStream inputStream = new FileInputStream(file);

    Workbook workbook = null;

    //Find the file extension by splitting file name in substring  and getting only extension name
    String fileExtensionName = fileName.substring(fileName.indexOf("."));

    //Check condition if the file is xlsx file
    if(fileExtensionName.equals(".xlsx")){
    	workbook = new XSSFWorkbook(inputStream);
    }
   
    //Check condition if the file is xls file
    else if(fileExtensionName.equals(".xls")){
        workbook = new HSSFWorkbook(inputStream);
    }
    else
    	System.err.print("Error : File is not an excel file");
   
    //Read sheet inside the workbook by its name
	Sheet sheet = workbook.getSheet(sheetName);
	return sheet;
	}
    
    
    public Map<String, String> getDataInMap(Sheet sheet, int startRowNo, int startColNo) {
	     //Find number of rows in excel file
    	startRowNo = startRowNo-1;
    	startColNo = startColNo-1;
	     int lastCellNum = sheet.getRow(startRowNo).getLastCellNum();
	     System.out.println(sheet.getRow(startRowNo).getLastCellNum() - startColNo + "    sheet.getRow(startRowNo).getLastCellNum() - startColNo");
	
	    //Create a loop over all the rows of excel file to read it
	    Map<String,String> map = new LinkedHashMap<String,String>();
	
	    for (int i = startColNo; i < lastCellNum; i++) {
	        String key = sheet.getRow(startRowNo).getCell(i).toString();
	        String value = sheet.getRow(startRowNo+1).getCell(i).toString();
	        map.put(key, value);
	        System.out.println(key+" , "+value);
	        }
		return map;
	    }
    
    public String getValue(Map<String, String> map, String key) {
    	String value;
		if(map.containsKey(key))
    		value = (String) map.get(key);
    	else
    		value = null;
		return value;
    }
    
    public static String readFile(String filepath) {
	    String data ="";
		try {
			data = new String(Files.readAllBytes(Paths.get(filepath)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
    }
	
    public Map<String, String> convertToMap(String data) {
		Map<String, String> parametersMap = new LinkedHashMap<String, String>();
		String[] datasplit = data.split("\\r\\n");
		for(String spl : datasplit){
			parametersMap.put(spl.split(":")[0],spl.split(":")[1]);
		}
		return parametersMap;
	}
    }