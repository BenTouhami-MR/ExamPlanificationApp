package com.exam.core.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.exam.core.web.models.ErrorResponse;

@RestControllerAdvice
public class AppGlobalExceptionHandler {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Value("${gsabs.app.mode}")
	private String appMode;

	// Generic Exception Handler
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception exc, HttpServletRequest req) {
		LOGGER.error("Exception: ", exc);

		// Prepare error message based on the environment
		String errorMsg;
		if ("PROD".equals(appMode)) {
			errorMsg = "The application cannot execute the action because it encountered an error. Please contact the administrator.";
		} else { // DEV mode
			LOGGER.warn("Dev mode is active");
			errorMsg = "Error occurred: " + exc.getMessage();
		}

		// Create error response object
		ErrorResponse errorResponse = new ErrorResponse(errorMsg, req.getRequestURL().toString());

		// Return JSON error response with status code 500 (Internal Server Error)
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Custom Exception Handler for SalleIndisponibleException
	@ExceptionHandler(SalleIndisponibleException.class)
	public ResponseEntity<ErrorResponse> handleSalleIndisponibleException(SalleIndisponibleException exc, HttpServletRequest req) {
		LOGGER.error("SalleIndisponibleException: ", exc);

		// Use exception's message for the error
		String errorMsg = exc.getMessage();

		// Create error response object
		ErrorResponse errorResponse = new ErrorResponse(errorMsg, req.getRequestURL().toString());

		// Return JSON error response with status code 409 (Conflict)
		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}
}
