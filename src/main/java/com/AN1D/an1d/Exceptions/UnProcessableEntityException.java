package com.AN1D.an1d.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnProcessableEntityException extends RuntimeException{

    private static final long serialVersionUID = 1L;
	public UnProcessableEntityException(String message) {
		super(message);
	}
}
