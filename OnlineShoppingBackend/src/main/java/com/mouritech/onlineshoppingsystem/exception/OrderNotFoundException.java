package com.mouritech.onlineshoppingsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6590261953235660066L;

	public OrderNotFoundException(String msg) {
		
		super(msg);
	}
}
