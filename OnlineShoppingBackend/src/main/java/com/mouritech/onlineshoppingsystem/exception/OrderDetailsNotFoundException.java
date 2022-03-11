package com.mouritech.onlineshoppingsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OrderDetailsNotFoundException extends RuntimeException {


	private static final long serialVersionUID = -6013342368647252274L;
public OrderDetailsNotFoundException(String msg) {
	super(msg);
}

}
