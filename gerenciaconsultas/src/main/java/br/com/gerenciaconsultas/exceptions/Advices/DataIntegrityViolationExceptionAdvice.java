package br.com.gerenciaconsultas.exceptions.Advices;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.gerenciaconsultas.exceptions.DataIntegrityViolationException;
import br.com.gerenciaconsultas.exceptions.Message.MessageException;

@ControllerAdvice
public class DataIntegrityViolationExceptionAdvice {
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<MessageException> dataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {

        MessageException error = new MessageException(new Date(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}