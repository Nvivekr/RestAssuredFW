package testutilities;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import testResources.POJOs.ResponseMatcher;

public class ResponseExtractor extends ResponseMatcher{
	
	Utility util = new Utility();


	public static Response getResponse(RequestSpecification request) throws IOException {

		Response response = given().relaxedHTTPSValidation().spec(request).get();
		try {
			response.prettyPrint();
		}
			catch (Exception je) {
			System.out.println(je.toString());
		}
		return response;
	}
	
	public static Response postResponse(RequestSpecification request) throws IOException {
		request
//		.config(RestAssured.config()
//                .encoderConfig(EncoderConfig.encoderConfig()
//                		.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
		.log().all();
		Response response = given().relaxedHTTPSValidation().spec(request).post();
		
		try {
			System.out.println("RESPONSE BODY:");
			response.prettyPrint();
			System.out.println("STATUS: "+response.statusCode());
		}
			catch (Exception je) {
			System.out.println(je.toString());
		}
		return response;
	}

}