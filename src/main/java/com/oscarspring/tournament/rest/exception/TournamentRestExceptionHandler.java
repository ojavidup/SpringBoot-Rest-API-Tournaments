package com.oscarspring.tournament.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TournamentRestExceptionHandler {
	
	//Add an exception handler for CustomerNotFoundException
		@ExceptionHandler
		public ResponseEntity<TournamentErrorResponse> handleException(TournamentNotFoundException exc) {
			
			//create CustomerErrorResponse
			TournamentErrorResponse error = new TournamentErrorResponse(HttpStatus.NOT_FOUND.value(),
																	exc.getMessage(),
																	System.currentTimeMillis());
			
			
			//return ResponseEntity
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
		
		//Add another exception handler ... to catch any exception (catch all)
		@ExceptionHandler
		public ResponseEntity<TournamentErrorResponse> handleException(Exception exc) {
			
			//create CustomerErrorResponse
			TournamentErrorResponse error = new TournamentErrorResponse(HttpStatus.BAD_REQUEST.value(),
																	exc.getMessage(),
																	System.currentTimeMillis());
			
			
			//return ResponseEntity
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

}
