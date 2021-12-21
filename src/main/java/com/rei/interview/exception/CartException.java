package com.rei.interview.exception;

/**
 * Customized Cart Exception
 * @author IBarreto
 *
 */
public class CartException extends RuntimeException{

	/**
	 * Generated serial version UID 
	 */
	private static final long serialVersionUID = -7455387676182119955L;

	public CartException(String id) {
		super("Something went wrong with the Cart" + id);
	}
}
