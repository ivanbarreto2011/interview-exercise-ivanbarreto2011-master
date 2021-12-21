package com.rei.interview.rs.product;

public class ProductNotFoundException extends RuntimeException{

	/**
	 * generate serial version UID
	 */
	private static final long serialVersionUID = -4145067638308182857L;
	
	ProductNotFoundException(String id) {
	    super("Could not find product " + id);
	}

}
