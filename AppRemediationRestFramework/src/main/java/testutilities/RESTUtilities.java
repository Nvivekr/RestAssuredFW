package testutilities;


import io.restassured.response.Response;

public class RESTUtilities {
	
	Utility util = new Utility();
	    
    public int getStatus(Response response) {
    	int status = response.getStatusCode();
    	return status;
    }
    
//    public String postAccessToken(Response response) {
//    	String access_token = response.getBody().jsonPath().getString("access_token");
//    	return access_token;
//    }
    
//    public String geteLoc(Response response) {
//    	String eLoc = response.getBody().jsonPath().getString("suggestedLocations[0].distance");
//    	return eLoc;
//    }
    
    public String getElementFromJSONResponse(Response response, String element) {
    	String value =null;
		try {
			value = response.getBody().jsonPath().getString(element);
			 if(value==null) {
				 value = "Attribute Not Available";
			 }
		}
		catch (Exception e) {
			if(value==null) {
				 value = "Attribute Not Available";
			 }
		} 
		 return value;
	}
    
}