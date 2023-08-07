package com.example.reservasapi.service.exceptions;

public class DateNotAvailableException extends RuntimeException {

    public DateNotAvailableException(Object date) {
        super("Esta data já está reservada. Disponibilidade para reservas somente após o dia " + date);
    }

}
