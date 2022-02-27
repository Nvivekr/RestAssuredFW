package testResources.POJOs.ReqresUtils;

import com.google.gson.annotations.SerializedName;

/**
 * Pojo for initializing all parameters which will be used in the code
 * @author vnandika
 *
 */
public class ParamPoJoforReqres {
	//use Source, create getters and setters after you declare a variable here
	
	@SerializedName("pagevalue")private String pagevalue;

	public String getPagevalue() {
		return pagevalue;
	}

	public void setPagevalue(String pagevalue) {
		this.pagevalue = pagevalue;
	}

	


}
