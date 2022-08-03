package com.api.boxwatch.callervolume.errorhandling;

public class EmptyDataException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2377169494796152003L;

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
