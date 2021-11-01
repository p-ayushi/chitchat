package com.ayu.chitchat.models;

public class Result {
	private Integer status;
	private Boolean success;
	private String message;
	private Object response;

	public Result() {
	}

	public Result(Integer status, Boolean success, String message, Object response) {
		super();
		this.status = status;
		this.success = success;
		this.message = message;
		this.response = response;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "Result [status=" + status + ", success=" + success + ", message=" + message + ", response=" + response
				+ "]";
	}

}
