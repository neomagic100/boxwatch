package com.api.boxwatch.timesaved.errorhandling;

public class EmptyDataException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8492452321209117017L;

	public EmptyDataException(String message) {
		super(message);
	}

	public EmptyDataException() {
		super("The list of data retrieved is empty");
	}

	@Override
	public Throwable fillInStackTrace() {
		return this;
	}
}
