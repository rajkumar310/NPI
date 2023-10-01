package com.npi.exception;

public class WrongPasswordException  extends Exception{
	private static final long serialVersionUID = 1L;
    /**
     * 
     * @param msg
     */
	public WrongPasswordException(String msg) {
		super(msg);
	}

}
