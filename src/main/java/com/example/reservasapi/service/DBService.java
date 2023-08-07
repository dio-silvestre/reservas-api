package com.example.reservasapi.service;

import com.example.reservasapi.domain.Reserva;
import com.example.reservasapi.domain.enums.ReservaStatus;
import com.example.reservasapi.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Bean
    public void instanciaDB() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Reserva reserva1 = new Reserva(null, "Fulano de Tal", sdf.parse("2023-08-07"), sdf.parse("2023-08-07"), 2, ReservaStatus.CONFIRMADA);
        Reserva reserva2 = new Reserva(null, "Ciclano de Tal", sdf.parse("2023-08-15"), sdf.parse("2023-08-16"), 4, ReservaStatus.CONFIRMADA);

        reservaRepository.saveAll(Arrays.asList(reserva1, reserva2));
    }

}
