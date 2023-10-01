package com.npi.exception;

public class DuplicateUserFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    /**
     * 
     * @param msg
     */
	public DuplicateUserFoundException(String msg) {
		super(msg);
	}


}
