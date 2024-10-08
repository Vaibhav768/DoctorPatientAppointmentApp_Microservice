package com.nt.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nt.exception.DoctorNotFoundException;
import com.nt.exception.PatientNotFoundException;
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

   @ExceptionHandler(DoctorNotFoundException.class)
   public ResponseEntity<String> handleDoctorNotFound(DoctorNotFoundException ex) {
       return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
   }

   @ExceptionHandler(PatientNotFoundException.class)
   public ResponseEntity<String> handlePatientNotFound(PatientNotFoundException ex) {
       return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
   }
   
   // Handle other exceptions globally
   @ExceptionHandler(Exception.class)
   public ResponseEntity<String> handleGlobalException(Exception ex) {
       return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
   }
}
