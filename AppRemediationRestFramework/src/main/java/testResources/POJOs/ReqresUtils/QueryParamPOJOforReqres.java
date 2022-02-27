package testResources.POJOs.ReqresUtils;

import com.google.gson.annotations.SerializedName;

public class QueryParamPOJOforReqres {
	@SerializedName("page") private String Pagenumber;

	public String getPagenumber() {
		return Pagenumber;
	}

	public void setPagenumber(String pagenumber) {
		this.Pagenumber = pagenumber;
	}
	

}
