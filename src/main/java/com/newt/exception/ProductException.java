package com.newt.exception;

import org.springframework.validation.FieldError;

public class ProductException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	FieldError error;

	public ProductException(FieldError error) {
		super(error.getField() + " " + error.getCode() + " " + error.getDefaultMessage());
		this.error = error;
	}

	public FieldError getError() {
		return error;
	}

}
