package com.example.reservasapi.service.exceptions;

public class ReservaNotFoundException extends RuntimeException {
    public ReservaNotFoundException(Object id) {
        super("Reserva não encontrada. Id: " + id);
    }
}
