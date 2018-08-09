package com.dream.ssh.vo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MyResult {
	private final static Logger LOG = LogManager.getLogger(MyResult.class);
	private Boolean success=false;
	private String message="sorry!";
	public Boolean getSuccess() {
		return success;
	}
	public MyResult setSuccess(Boolean success) {
		this.success = success;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public MyResult setMessage(String message) {
		this.message = message;
		return this;
	}
	
	
}
