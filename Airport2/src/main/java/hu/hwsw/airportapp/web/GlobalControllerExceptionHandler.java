package hu.hwsw.airportapp.web;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import hu.hwsw.airportapp.web.dto.ErrorDTO;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler(NoSuchElementException.class)
	ResponseEntity<ErrorDTO> handleNoSuchElementException(NoSuchElementException exception) {
		ErrorDTO error = new ErrorDTO(404, exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}	
}