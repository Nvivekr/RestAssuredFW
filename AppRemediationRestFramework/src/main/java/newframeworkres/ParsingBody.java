package newframeworkres;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import io.restassured.path.xml.XmlPath;
import testutilities.FileReaderuserdef;

public class ParsingBody {
	
	public String jsonread(String path,String element) throws IOException, ParseException {
		// TODO Auto-generated method stub
		FileReader reader = new FileReader(path);
	    JSONParser parser = new JSONParser();
	    String data = parser.parse(reader).toString();
	    DocumentContext doc = JsonPath.parse(data);
	    System.out.println(doc.jsonString());
	    System.out.println(doc.read(element));
	    String output=doc.read(element);
	    System.out.println(output);
//	   doc.set("$['payload']['fund_account.validation']['entity']['notes']['transactionId']",id);
	    System.out.println(doc.jsonString());
	    return output;
	}
	
	public Boolean jsonset(String path,String element,String replaceval) throws IOException, ParseException {
		// TODO Auto-generated method stub
		FileReader reader = new FileReader(path);
	    JSONParser parser = new JSONParser();
	    String data = parser.parse(reader).toString();
	    DocumentContext doc = JsonPath.parse(data);
	    System.out.println(doc.jsonString());
	    System.out.println(doc.read(element));
	    doc.set(element,replaceval);
	    System.out.println(doc.jsonString());
	    return true;
	}
	
	public String readXML(String path) {
		String body=FileReaderuserdef.readFile(path);
		String xmldata=XmlPath.with(body).getString("WorkPackageGrps.Status");
		return xmldata;
	}
	
	public String setXML(String path, String orgval, String repval) {
		String body=FileReaderuserdef.readFile(path);
		body=body.replace(orgval, repval);
		return body;
	}

}
