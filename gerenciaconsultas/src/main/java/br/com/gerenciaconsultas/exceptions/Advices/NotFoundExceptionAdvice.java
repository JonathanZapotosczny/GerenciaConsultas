package br.com.gerenciaconsultas.exceptions.Advices;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.gerenciaconsultas.exceptions.NotFoundException;
import br.com.gerenciaconsultas.exceptions.Message.MessageException;

@ControllerAdvice
public class NotFoundExceptionAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MessageException> NotFoundException(NotFoundException ex, HttpServletRequest request) {

        MessageException error = new MessageException(new Date(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}