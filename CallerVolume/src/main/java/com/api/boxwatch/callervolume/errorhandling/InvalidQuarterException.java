package com.api.boxwatch.callervolume.errorhandling;

public class InvalidQuarterException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7102465928919352791L;
	private String message;
	
	public InvalidQuarterException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	@Override
	public Throwable fillInStackTrace() {
		return this;
	}
}
