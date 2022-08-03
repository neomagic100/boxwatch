package com.api.boxwatch.callervolume.errorhandling;

public class InvalidDateException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1130419828331269583L;
	
	public InvalidDateException(String message) {
		super(message);
	}
	
	public InvalidDateException() {
		super("Invalid Date");
	}

}
