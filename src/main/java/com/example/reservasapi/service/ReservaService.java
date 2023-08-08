package com.example.reservasapi.service;

import com.example.reservasapi.domain.Reserva;
import com.example.reservasapi.domain.enums.ReservaStatus;
import com.example.reservasapi.repository.ReservaRepository;
import com.example.reservasapi.service.exceptions.DateNotAvailableException;
import com.example.reservasapi.service.exceptions.IncompatibleDatesException;
import com.example.reservasapi.service.exceptions.InvalidDateException;
import com.example.reservasapi.service.exceptions.ReservaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva insert(Reserva obj) {
        Optional<Reserva> foundObj = Optional.ofNullable(reservaRepository.findByDateIfBetweenDates(obj.getDataInicio()));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date dataAtual = new Date();

        if (foundObj.isPresent() && foundObj.get().getStatus() != ReservaStatus.CANCELADA) {
            throw new DateNotAvailableException(sdf.format(foundObj.get().getDataFim()));
        }

        if (dataAtual.compareTo(obj.getDataInicio()) > 0) {
            throw new InvalidDateException(sdf.format(dataAtual));
        }

        if (obj.getDataInicio().compareTo(obj.getDataFim()) > 0) {
            throw new IncompatibleDatesException(sdf.format(obj.getDataInicio()), sdf.format(obj.getDataFim()));
        }

        obj.setId(null);
        return reservaRepository.save(obj);
    }

    public List<Reserva> findAll() {
        return reservaRepository.findAllOrderedByDate();
    }

    public Reserva findById(Integer id) {
        Optional<Reserva> obj = reservaRepository.findById(id);
        return obj.orElseThrow(() -> new ReservaNotFoundException(id));
    }

    public Reserva update(Integer id, Reserva obj) {
        Reserva newObj = findById(id);
        updateData(newObj, obj);
        return reservaRepository.save(newObj);
    }

    private void updateData(Reserva novo, Reserva antigo) {
        novo.setNomeHospede(antigo.getNomeHospede());
        novo.setStatus(antigo.getStatus());
    }

    public Reserva delete(Integer id) {
        Reserva obj = findById(id);
        obj.setStatus(ReservaStatus.CANCELADA);
        return reservaRepository.save(obj);
    }
}
