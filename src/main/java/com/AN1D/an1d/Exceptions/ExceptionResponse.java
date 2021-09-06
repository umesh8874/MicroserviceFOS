package com.AN1D.an1d.Exceptions;
import java.util.Date;

public class ExceptionResponse {
	
	private Date timestamp;
	private String message;
	private String path;
	
	public ExceptionResponse(Date timestamp, String message, String path) {
		this.timestamp = timestamp;
		this.message = message;
		this.path = path;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}