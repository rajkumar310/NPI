package com.npi.exception;

public class UserNotFoundException  extends Exception{
	private static final long serialVersionUID = 1L;
    /**
     * 
     * @param msg
     */
	public UserNotFoundException(String msg) {
		super(msg);
	}


}
