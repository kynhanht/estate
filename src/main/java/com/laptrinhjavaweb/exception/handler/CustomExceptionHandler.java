package com.laptrinhjavaweb.exception.handler;


import com.laptrinhjavaweb.exception.InternalException;
import com.laptrinhjavaweb.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;

@RestControllerAdvice("com.laptrinhjavaweb.api")
public class CustomExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException ex, WebRequest request) {

        logger.error("Runtime error: {}", ex.getMessage());

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());

        return new ResponseEntity<ErrorMessage>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }


    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<ErrorMessage> handleMethodNotAllowedException(MethodNotAllowedException ex, WebRequest request) {

        logger.error("Runtime error: {}", ex.getMessage());

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());

        return new ResponseEntity<ErrorMessage>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {

        logger.error("Runtime error: {}", ex.getMessage());

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());

        return new ResponseEntity<ErrorMessage>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

    @ExceptionHandler({Exception.class, InternalException.class})
    public ResponseEntity<ErrorMessage> handleAllException(Exception ex, WebRequest request) {

        logger.error("Runtime error: {}", ex.getMessage());

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());

        return new ResponseEntity<ErrorMessage>(errorMessage, new HttpHeaders(), errorMessage.getStatus());

    }

}
