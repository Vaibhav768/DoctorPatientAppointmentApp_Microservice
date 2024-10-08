package com.nt.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nt.exception.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	/*===========================================================================
	 global exception handler advice class
	 ============================================================================*/
	 // Handle EntityNotFoundException
   @ExceptionHandler(ResourceNotFoundException.class)
   public ResponseEntity<String> handleEntityNotFound(ResourceNotFoundException ex) {
       return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
   }

   // Handle IllegalArgumentException
   @ExceptionHandler(IllegalArgumentException.class)
   public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
       return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
   }

   // Handle other exceptions globally
   @ExceptionHandler(Exception.class)
   public ResponseEntity<String> handleGlobalException(Exception ex) {
       return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
   }
}
