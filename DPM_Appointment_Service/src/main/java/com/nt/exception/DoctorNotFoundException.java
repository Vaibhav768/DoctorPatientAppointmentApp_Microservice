package com.nt.exception;

public class DoctorNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	public DoctorNotFoundException(String message) {
        super(message);
    }
	
	public DoctorNotFoundException() {
        super();
    }
}
