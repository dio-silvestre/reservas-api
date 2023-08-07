package com.example.reservasapi.service;

import com.example.reservasapi.domain.Reserva;
import com.example.reservasapi.domain.enums.ReservaStatus;
import com.example.reservasapi.repository.ReservaRepository;
import com.example.reservasapi.service.exceptions.ReservaNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaService reservaService;

    @Test
    public void testCreateReserva() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Reserva reserva = new Reserva(1, "Fulano de Tal", sdf.parse("2023-08-07"), sdf.parse("2023-08-07"), 2, ReservaStatus.CONFIRMADA);

        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        Reserva reservaInserida = reservaService.insert(reserva);

        assertNotNull(reservaInserida);
        assertNull(reservaInserida.getId());
        assertEquals(reserva.getNomeHospede(), reservaInserida.getNomeHospede());
        assertEquals(reserva.getQuantidadePessoas(), reservaInserida.getQuantidadePessoas());
        assertEquals(reserva.getDataInicio().getClass(), reservaInserida.getDataInicio().getClass());
        assertEquals(reserva.getDataFim().getClass(), reservaInserida.getDataFim().getClass());
        assertEquals(reserva.getStatus(), reservaInserida.getStatus());

        verify(reservaRepository, times(1)).save(any(Reserva.class));
    }

    @Test
    public void testFindReservaByIdExistente() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer reservaId = 1;
        Reserva reserva = new Reserva(reservaId, "Fulano de Tal", sdf.parse("2023-08-07"), sdf.parse("2023-08-07"), 2, ReservaStatus.CONFIRMADA);

        when(reservaRepository.findById(reservaId)).thenReturn(Optional.of(reserva));

        Reserva reservaEncontrada = reservaService.findById(reservaId);

        assertNotNull(reservaEncontrada);
        assertEquals(reserva.getId(), reservaEncontrada.getId());
        assertEquals(reserva.getNomeHospede(), reservaEncontrada.getNomeHospede());
        assertEquals(reserva.getQuantidadePessoas(), reservaEncontrada.getQuantidadePessoas());
        assertEquals(reserva.getDataInicio().getClass(), reservaEncontrada.getDataInicio().getClass());
        assertEquals(reserva.getDataFim().getClass(), reservaEncontrada.getDataFim().getClass());
        assertEquals(reserva.getStatus(), reservaEncontrada.getStatus());

        verify(reservaRepository, times(1)).findById(reservaId);
    }

    @Test
    public void testFindReservaByIdNaoExistente(){
        Integer reservaId = 99;

        when(reservaRepository.findById(reservaId)).thenReturn(Optional.empty());
        assertThrows(ReservaNotFoundException.class, () -> reservaService.findById(reservaId));
        verify(reservaRepository, times(1)).findById(reservaId);
    }

    @Test
    public void testDeleteReserva() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer reservaId = 1;
        Reserva reservaAtualizada = new Reserva(reservaId, "Fulano de Tal", sdf.parse("2023-08-07"), sdf.parse("2023-08-07"), 2, ReservaStatus.CANCELADA);
        Reserva reservaExistente = new Reserva(reservaId, "Fulano de Tal", sdf.parse("2023-08-07"), sdf.parse("2023-08-07"), 2, ReservaStatus.CONFIRMADA);

        when(reservaRepository.findById(reservaId)).thenReturn(Optional.of(reservaExistente));
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reservaAtualizada);

        Reserva resultado = reservaService.delete(reservaId);

        assertNotNull(resultado);
        assertEquals(reservaId, resultado.getId());
        assertEquals(reservaAtualizada.getNomeHospede(), resultado.getNomeHospede());
        assertEquals(reservaAtualizada.getDataInicio(), resultado.getDataInicio());
        assertEquals(reservaAtualizada.getDataFim(), resultado.getDataFim());
        assertEquals(reservaAtualizada.getQuantidadePessoas(), resultado.getQuantidadePessoas());
        assertEquals(resultado.getStatus(), ReservaStatus.CANCELADA);

        verify(reservaRepository, times(1)).findById(reservaId);
        verify(reservaRepository, times(1)).save(any(Reserva.class));
    }

    @Test
    public void testUpdateReserva() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer reservaId = 1;
        Reserva reservaAtualizada = new Reserva(reservaId, "Fulano de Tal", sdf.parse("2023-08-08"), sdf.parse("2023-08-08"), 2, ReservaStatus.PENDENTE);
        Reserva reservaExistente = new Reserva(reservaId, "Fulano de Tal", sdf.parse("2023-08-08"), sdf.parse("2023-08-08"), 2, ReservaStatus.CONFIRMADA);

        when(reservaRepository.findById(reservaId)).thenReturn(Optional.of(reservaExistente));
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reservaAtualizada);

        Reserva resultado = reservaService.update(reservaId, reservaAtualizada);

        assertNotNull(resultado);
        assertEquals(reservaId, resultado.getId());
        assertEquals(reservaAtualizada.getNomeHospede(), resultado.getNomeHospede());
        assertEquals(reservaAtualizada.getDataInicio(), resultado.getDataInicio());
        assertEquals(reservaAtualizada.getDataFim(), resultado.getDataFim());
        assertEquals(reservaAtualizada.getQuantidadePessoas(), resultado.getQuantidadePessoas());
        assertEquals(resultado.getStatus(), ReservaStatus.PENDENTE);

        verify(reservaRepository, times(1)).findById(reservaId);
        verify(reservaRepository, times(1)).save(any(Reserva.class));
    }
}
