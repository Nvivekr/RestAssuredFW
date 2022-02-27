package testutilities;

import io.restassured.response.Response;


public class SOAPUtilities {
	
	Utility util = new Utility();
	
	public String getElementFromXMLResponse(Response response, String element) {
		String value =null;
		try {
			value = response.getBody().xmlPath().getString(element);
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
	
	public int getStatus(Response response) {
    	int status = response.getStatusCode();
    	return status;
    }
	
}