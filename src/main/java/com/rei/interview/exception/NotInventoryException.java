package com.rei.interview.exception;

/**
 * Customized exception for Not Inventory
 * @author IBarreto
 *
 */
public class NotInventoryException extends RuntimeException{
	
	/**
	 * Generated serial version UID 
	 */
	private static final long serialVersionUID = -219499227515380916L;

	public NotInventoryException(String id) {
	    super("Could not find product inventory" + id);
	}
}
