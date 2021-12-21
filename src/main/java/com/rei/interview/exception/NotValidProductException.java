package com.rei.interview.exception;

public class NotValidProductException extends RuntimeException{
	
	
	/**
	 * Customized exception for Not valid product
	 */
	private static final long serialVersionUID = 2986577043715796802L;

	public NotValidProductException(String id) {
		super("This product is not a valid Product" + id);
	}
}
