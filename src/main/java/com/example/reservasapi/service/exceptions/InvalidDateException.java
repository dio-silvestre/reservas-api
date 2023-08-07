package com.example.reservasapi.service.exceptions;

public class InvalidDateException extends RuntimeException {

    public InvalidDateException(Object date) {
        super("Não é possível agendar para uma data passada. Data atual: " + date);
    }

}
