package testResources.POJOs;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public class ResponseClass {
	public void getResponse(String url, String path, Map<String, String> pathparam, 
			Map<String, String> qparam, List<Header> listofHeaders) throws IOException {
		RestAssured.baseURI = url;
		Headers headers = new Headers(listofHeaders);
		RequestSpecification request = given()
				.relaxedHTTPSValidation()
				.headers(headers);
		if(pathparam!=null) {
			request.pathParams(pathparam);
		}
		if(qparam!=null) {
			request.queryParams(qparam);
		}
		
		
//		System.out.println(request.request());
		QueryableRequestSpecification queryable = SpecificationQuerier.query(request);
		System.out.println("Header is: " + queryable.getHeaders());
		System.out.println("baseuri are : "+ queryable.getBaseUri());
		System.out.println("basepath are : "+ queryable.getBasePath());
		System.out.println("params are : "+ queryable.getPathParams());
		System.out.println("quparams are : "+ queryable.getQueryParams());
//		System.out.println("whole req are : "+ queryable.);
//		Response response = request.get(path);
//		
//		try {
//			response.prettyPrint();
//		
//		}
//			catch (Exception je) {
//			System.out.println(je.toString());
//		}
//		
//		return response;
	}

}
