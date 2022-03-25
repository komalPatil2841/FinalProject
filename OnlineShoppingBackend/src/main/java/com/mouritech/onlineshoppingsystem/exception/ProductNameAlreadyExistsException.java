package com.mouritech.onlineshoppingsystem.exception;

public class ProductNameAlreadyExistsException extends Exception {


	private static final long serialVersionUID = 8783044693784634481L;
          
	 public ProductNameAlreadyExistsException(String msg) {
		 super(msg);
	 }
}
