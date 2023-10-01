package com.npi.exceptionHandling;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.npi.exception.IdNotFoundException;
import com.npi.exception.UpdateFailedException;

public class GlobalExceptionHandler {
	/**
	 * 
	 * @param e
	 * @return
	 */

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

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<?> handleIdNotFoundException(IdNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UpdateFailedException.class)
	public ResponseEntity<?> handleUpdateFailedException(UpdateFailedException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}


}
