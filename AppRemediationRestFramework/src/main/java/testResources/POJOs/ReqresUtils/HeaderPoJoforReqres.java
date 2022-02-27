package testResources.POJOs.ReqresUtils;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class HeaderPoJoforReqres {
	@SerializedName("Accept")private String accepttype;
	@SerializedName("Content-Type")private String content_type;

	public String getAccepttype() {
		return accepttype;
	}


	public void setAccepttype(String accepttype) {
		this.accepttype = accepttype;
	}

	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}






}

