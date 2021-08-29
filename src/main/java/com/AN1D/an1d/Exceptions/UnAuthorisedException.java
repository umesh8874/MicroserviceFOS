package com.AN1D.an1d.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorisedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public UnAuthorisedException(String message) {
		super(message);
	}
}
