package com.little_brainees.app.ws.exceptions;

public class DuplicateRecordFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 4L;
	public DuplicateRecordFoundException(String errorMessage) {
		super(errorMessage);
	}

}
