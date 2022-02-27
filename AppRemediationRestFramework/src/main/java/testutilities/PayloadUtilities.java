package testutilities;

import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Map;

import com.aventstack.extentreports.Status;

import reporting.ReportManager;
import reporting.Reporter;


public class PayloadUtilities {
	
	
	private Reporter reporter;

	public Map<String, String> getPayloadData(String filepath) {
		reporter = ReportManager.getInstance().getReporter();
		if(!new File(filepath).exists()) {
			reporter.log(Status.FAIL," - File Not Found" + filepath);
		}
		testutilities.FileReaderuserdef filereader = FileReaderManager.getInstance().getFileReader();
		String payload = filereader.readFile(filepath);
		String[] payloadarray = payload.split("(?m)^\\s*$");
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (String str : payloadarray) {
			str = str.trim();
			String key = str.split(":")[0].trim();
			int keylength = key.length();
			String value = str.substring(keylength+1).trim();
			map.put(key, value);
			}
		return map;
	}
	
	public Map<String, String> getheaders(Map<String, String> payloaddata) {  //will be made into a POJO and converted map->list
		String string = payloaddata.get("headers");
		if(string.trim().equals("")) {
			reporter.log(Status.FAIL, " - Request Headers Not Available");
		}
		String key,value = "";
		String[] stringarray = string.split("\\R"); 
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (String str : stringarray) {
			key = str.split(":")[0];
			value = str.split(":")[1];
			map.put(key, value);
		}
		return map;
	}
	
	public Map<String, String> getqueryparam(Map<String, String> payloaddata) { //will be made into a POJO and converted to map
		String string = payloaddata.get("queryparam");
		if(string.trim().equals("")) {
			reporter.log(Status.FAIL, " - Query Param Not Available");
		}
		String key,value = "";
		String[] stringarray = string.split("\\R"); 
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (String str : stringarray) {
			key = str.split(":")[0];
			value = str.split(":")[1];
			map.put(key, value);
		}
		return map;
	}
	
	public Map<String, String> getpathparam(Map<String, String> payloaddata) {
		String string = payloaddata.get("pathparam");
		if(string.trim().equals("")) {
			reporter.log(Status.FAIL, " - Path Param Not Available");
		}
		String key,value = "";
		String[] stringarray = string.split("\\R"); 
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (String str : stringarray) {
			key = str.split(":")[0];
			value = str.split(":")[1];
			map.put(key, value);
		}
		return map;
	}
	
	public String getrequestbody(Map<String, String> payloaddata) {
		String string = payloaddata.get("requestbody");
		if(string.trim().equals("")) {
			reporter.log(Status.INFO, "No Request Body");
		}
		return string;
	}

}
