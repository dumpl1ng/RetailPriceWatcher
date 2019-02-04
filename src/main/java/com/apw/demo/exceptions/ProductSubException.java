package com.apw.demo.exceptions;

import java.io.Serializable;

public class ProductSubException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8875765544332357982L;
	
	public ProductSubException(String message) {
		super(message);
	}
}
