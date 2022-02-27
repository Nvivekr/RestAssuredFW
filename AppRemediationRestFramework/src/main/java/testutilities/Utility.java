package testutilities;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

    String ENV = "TEST";
    FileInputStream fileInputStream = null;

    public Properties readTestData() {
        Properties prop = new Properties();
        try {
            File f = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\GlobalProperties.properties");
//            System.out.println("user directory is " + System.getProperty("user.dir") + "/src/test/resources/properties/GlobalProperties.properties");
            fileInputStream = new FileInputStream(f);
            prop.load(fileInputStream);
            //System.out.println("readTestData()");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            System.out.println(e.getMessage());
        }
        return prop;
    }

    // for passing and getting authentication token and returning it.
    public String getAuthToken(Properties prop) {
        RestAssured.baseURI = prop.getProperty("BASEURI_Test"); //variable
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        //Mentioning parameters in JSON body
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", prop.getProperty("username"));
        jsonObject.put("password", prop.getProperty("password"));

        //Sending parameters in JSON body
        request.body(jsonObject.toString());
        Response response = request.post(prop.getProperty("BASEPATH"));

        //Retrieving the status code of API
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Could not logged into United Airlines application");
        JsonPath jsonPath = response.jsonPath();
        String token = jsonPath.getString("Bearer token");
        System.out.println(token);
        return token;

    }
    
    //RegEx matcher if needed
    public String regEx(String strMatcher, String strPattern) {
		Pattern pattern = Pattern.compile(strPattern, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(strMatcher);
	    boolean matchFound = matcher.find();
	    if(matchFound) {
	    	return matcher.group();
	    } else {
	    	return "";
	    }
	}
    
    public Boolean regExMatch(String strMatcher, String strPattern) {
		Pattern pattern = Pattern.compile(strPattern, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(strMatcher);
	    boolean matchFound = matcher.find();
	    if(matchFound)
	    	return true;
	    return true;
	}
    
	public void waitformilliSec(int milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
