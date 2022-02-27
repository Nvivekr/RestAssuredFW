package testcases;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import baseClasses.DataUtility;
import baseClasses.TestBase;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import newframeworkres.HttpRequestType;
import pathinterface.ReqresPathsandurls;
import testResources.POJOs.ResponseMatcher;
import testutilities.FileReaderuserdef;

public class BasicTestcase extends TestBase implements ReqresPathsandurls{

	@Test(description = "demo with get function with reqres",
			dataProvider = "BasicTestcase",dataProviderClass = DataUtility.class)
	public void testcase1(String expectedstatus, String expectedvalue) throws IOException  {

		headersReqres.setAccepttype("*/*");  //setting values of headers
		headersReqres.setContent_type("application/json");
		
		paramsReqres.setPagevalue("2");
		
		Map<String,String> headers = mapingUtil.mapingheadersreturningasmap(headersReqres);
		Map<String, String> pathparam = mapingUtil.mapingparams(paramsReqres);		//Mapping the values of params
//		String body=FileReaderuserdef.readFile(System.getProperty("user.dir")+
//				"\\src\\main\\resources\\payloads\\demopayload");
		Response response = null;
		String url = reqresbaseuri;
		String path=reqresbasepath+"/{pagevalue}";
		
		try {
			response=new ResponseMatcher().responseManager(HttpRequestType.GET,url, 
					path,headers, null, pathparam,null);
			int status=response.getStatusCode();
			softAssert.sAssert(status, Integer.parseInt(expectedstatus), "status = " + status);
			String value = rutils.getElementFromJSONResponse(response,"data.first_name");
			softAssert.sAssert(value, expectedvalue, "idvalue = " + value);
//			softAssert.assertAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			reporter.log(Status.FAIL,e.getMessage());
		}
	}
	
	@Test(description = "demo with post function with reqres",
			dataProvider = "BasicTestcaseforpost",dataProviderClass = DataUtility.class)
	public void testcase2(String expectedstatus, String expectedvalue) throws IOException  {

		headersReqres.setContent_type("application/json");
		
		Map<String,String> headers = mapingUtil.mapingheadersreturningasmap(headersReqres);
//		Map<String, String> pathparam = mapingUtil.mapingparams(paramsReqres);		//Mapping the values of params
		
		Response response = null;
		String url = reqresbaseuri;
		String path=reqresbasepath;String body=FileReaderuserdef.readFile(payloadpath);
		
		try {
			response=new ResponseMatcher().responseManager(HttpRequestType.POST,url, 
					path,headers,body, null,null);
			int status=response.getStatusCode();
			softAssert.sAssert(status, Integer.parseInt(expectedstatus), "status = " + status);
			String value = rutils.getElementFromJSONResponse(response,"job");
			softAssert.sAssert(value, expectedvalue, "job value = " + value);
//			softAssert.assertAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			reporter.log(Status.FAIL,e.getMessage());
		}
	}
	

}
