package com.example.ToDo.error;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorResponseDTO> handleNoSuchElementException(NoSuchElementException exc) {
		ErrorResponseDTO error = createErrorResponse(exc.getMessage(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponseDTO> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exc) {
		ErrorResponseDTO error = createErrorResponse(exc.getMessage(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception exc) {
		ErrorResponseDTO error = createErrorResponse(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ErrorResponseDTO createErrorResponse(String message, HttpStatus status) {
		return ErrorResponseDTO.builder()
				.message(message)
				.status(status.value())
				.timestamp(LocalDateTime.now())
				.build();
	}
}
