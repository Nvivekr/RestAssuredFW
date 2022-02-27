package newframeworkres;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import io.restassured.http.Header;

public class MapingUtil {
	private Gson gson;
	/**
	 * Will convert HeadersPOJO to List of headers
	 * @param 
	 * @return Listofheaders in from of List
	 */
	public List<Header> mapingheaders(Object Headerobj){
		gson= new Gson();
		List<Header> listOfHeaders = new LinkedList<Header>();
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> map= gson.fromJson(gson.toJson(Headerobj), HashMap.class);  //Json-> hashmap
		Header header =null;
		for(Map.Entry<String, String> entry : map.entrySet()) {   //adding of headers to list of headers
			header=new Header(entry.getKey(), entry.getValue());
			listOfHeaders.add(header);
		}
		return listOfHeaders;
	}
	
	public Map<String,String> mapingheadersreturningasmap(Object Headerobj){
		gson= new Gson();
		@SuppressWarnings("unchecked")
		HashMap<String, String> map= gson.fromJson(gson.toJson(Headerobj), HashMap.class);  //Json-> hashmap
		return map;
	}
	
	/**
	 * Will convert pathparamPOJO to Map of pathparams
	 * @param PathparamPOJO
	 * @return pathparams in form of map
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> mapingparams(Object pathparamobj){
		gson= new Gson();
//		System.out.println(gson.toJson(pathparamobj));
 		LinkedHashMap<String, String> parammap=gson.fromJson(gson.toJson(pathparamobj), LinkedHashMap.class); //Gson-> hashmap
//		System.out.println(map);
		return parammap;
	}
	
	/**
	 * Will convert queryparamPOJO to Map of queryparams
	 * @param QueryParamPOJO
	 * @return queryparams in form of map
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> mapingqueryparams(Object queryparamobj){
		gson= new Gson();
//		System.out.println(gson.toJson(queryparamobj));
 		LinkedHashMap<String, String> queryparammap=gson.fromJson(gson.toJson(queryparamobj), LinkedHashMap.class); //Gson-> hashmap
//		System.out.println(map);
		return queryparammap;
	}


}
