package com.example.reservasapi.controller.exceptions;

import com.example.reservasapi.service.exceptions.DateNotAvailableException;
import com.example.reservasapi.service.exceptions.IncompatibleDatesException;
import com.example.reservasapi.service.exceptions.ReservaNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ReservaNotFoundException.class)
    public ResponseEntity<StandardError> reservaNotFound(ReservaNotFoundException e, HttpServletRequest request) {
        String error = "Reserva não encontrada";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(IncompatibleDatesException.class)
    public ResponseEntity<StandardError> incompatibleDates(IncompatibleDatesException e, HttpServletRequest request) {
        String error = "Data de entrada maior que data de saída";
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DateNotAvailableException.class)
    public ResponseEntity<StandardError> dateNotAvailable(DateNotAvailableException e, HttpServletRequest request) {
        String error = "Data não disponível para reserva";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> dateNotAvailable(ConstraintViolationException e, HttpServletRequest request) {
        String error = "Dados inválidos";
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
