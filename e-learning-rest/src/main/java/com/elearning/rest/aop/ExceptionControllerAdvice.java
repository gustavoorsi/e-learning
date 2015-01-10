package com.elearning.rest.aop;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.elearning.exception.EntityNotFoundException;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@ResponseBody
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	VndErrors courseNotFoundExceptionHandler(EntityNotFoundException ex) {
		return new VndErrors("Not Found", ex.getMessage());
	}

}
