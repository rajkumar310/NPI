package com.npi.exceptionHandling;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.npi.exception.DuplicateUserFoundException;



public class DuplicateUserFoundExceptionHandler {
	@ExceptionHandler(DuplicateUserFoundException.class)
	public ResponseEntity<String> accountExceptionHandler(DuplicateUserFoundException e) {

		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
    /**
     * 
     * @param e
     * @return
     */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationsExceptionsForAppointments(MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap<String, String>();
		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMsg = error.getDefaultMessage();
			errors.put(fieldName, errorMsg);
		});
		return errors;
	}

}
