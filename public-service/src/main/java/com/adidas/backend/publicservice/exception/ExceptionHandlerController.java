/**
 * 
 */
package com.adidas.backend.publicservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controller for handling exceptions
 * 
 * @author sushant
 *
 */

@ControllerAdvice
public class ExceptionHandlerController {

	/**
	 * This method will handle {@link MethodArgumentNotValidException}
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<String> badRequest(MethodArgumentNotValidException ex) {
		
		return ResponseEntity.badRequest().body(buildErrorMessage(ex));
		
	}

	private String buildErrorMessage(MethodArgumentNotValidException ex) {
		StringBuilder sb = new StringBuilder();
		for(FieldError fe : ex.getFieldErrors()) {
			sb.append(fe.getField());
			sb.append(" ");
			sb.append(fe.getDefaultMessage());
		}
		return sb.toString();
	}
}
