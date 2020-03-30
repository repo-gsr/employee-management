package com.employeemngt.model;

import com.employeemngt.util.ApiStatus;

public class ResponseObject {

	private Object responseData;

	private ApiStatus apiStatus;

	public Object getResponseData() {
		return responseData;
	}

	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}

	public ApiStatus getApiStatus() {
		return apiStatus;
	}

	public void setApiStatus(ApiStatus apiStatus) {
		this.apiStatus = apiStatus;
	}

}
