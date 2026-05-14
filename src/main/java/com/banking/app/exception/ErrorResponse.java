package com.banking.app.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ErrorResponse {

	private LocalDateTime timestamp;
	private String message;
	private HttpStatus status;
	private String path;
}
