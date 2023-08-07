package com.example.reservasapi.service.exceptions;

public class IncompatibleDatesException extends RuntimeException {

    public IncompatibleDatesException(Object start, Object end) {
        super("A data de entrada, " + start + ", não pode ser maior do que a data de saída, " + end);
    }
}
