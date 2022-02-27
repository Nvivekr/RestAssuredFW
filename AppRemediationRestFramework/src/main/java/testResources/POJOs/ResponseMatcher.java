package testResources.POJOs;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import newframeworkres.HttpRequestType;
import testutilities.ResponseExtractor;

public class ResponseMatcher {

	public Response responseManager(HttpRequestType type,String url, String path,
			Map<String,String> headers,String payload,Map<String, String> pathparam,Map<String, String> queryParameter) throws IOException{

		RequestSpecBuilder reqbuild = new RequestSpecBuilder();
		RequestSpecification request=reqbuild.build();
		QueryableRequestSpecification queryable = SpecificationQuerier.query(request);
		
		if(url!=null) {
			reqbuild.setBaseUri(url);System.out.println("baseuri are : "+ queryable.getBaseUri());
		}else { System.out.println("URI is null");}
		if(path!=null) {reqbuild.setBasePath(path);
		System.out.println("basepath are : "+ queryable.getBasePath());
		}else {System.out.println("Path is null");}
		if(!(headers==null)) {reqbuild.addHeaders(headers);
		System.out.println("Headers are : " + queryable.getHeaders());
		} else{System.out.println("Headers are null");}
		if(!(pathparam==null)) {reqbuild.addPathParams(pathparam);
		System.out.println("params are : "+ queryable.getPathParams());
		} else{System.out.println("Pathparams is null");}
		if(!(queryParameter==null)) {reqbuild.addQueryParams(queryParameter);
		System.out.println("quparams are : "+ queryable.getQueryParams());} 
		else{System.out.println("QueryPath is null");}
		if(payload!=null) {reqbuild.setBody(payload);
		System.out.println("Body is :"+queryable.getBody());
		}else{System.out.println("Body is null");}

		switch (type){
		case GET:
			if(request!=null){
				return ResponseExtractor.getResponse(request);}

		case POST:
			if(request!=null){
				return ResponseExtractor.postResponse(request);}
			
		default:
			throw new RuntimeException("Invalid request");

		}
	}
}
