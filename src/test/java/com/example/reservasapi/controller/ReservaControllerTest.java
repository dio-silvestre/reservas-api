package com.example.reservasapi.controller;

import com.example.reservasapi.domain.Reserva;
import com.example.reservasapi.domain.enums.ReservaStatus;
import com.example.reservasapi.service.ReservaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservaController.class)
public class ReservaControllerTest {

    @MockBean
    private ReservaService reservaService;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ReservaController reservaController;

    @Test
    public void testInsertReserva() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer reservaId = 1;
        Reserva reservaInserida = new Reserva(reservaId, "Fulano", sdf.parse("2023-08-07"), sdf.parse("2023-08-07"), 2, ReservaStatus.CONFIRMADA);

        when(reservaService.insert(any(Reserva.class))).thenReturn(reservaInserida);

        mockMvc.perform(MockMvcRequestBuilders.post("/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"nomeHospede\":\"Fulano\",\"dataInicio\":\"2023-08-07\",\"dataFim\":\"2023-08-07\",\"quantidadePessoas\":2,\"status\":\"CONFIRMADA\"}"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", "http://localhost/reservas/1"));
    }

    @Test
    public void testDeleteReserva() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/reservas/1/cancelar"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindReservaById() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer reservaId = 1;
        Reserva reservaEncontrada = new Reserva(reservaId, "Fulano", sdf.parse("2023-08-07"), sdf.parse("2023-08-07"), 2, ReservaStatus.CONFIRMADA);

        when(reservaService.findById(reservaId)).thenReturn(reservaEncontrada);

        mockMvc.perform(MockMvcRequestBuilders.get("/reservas/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nomeHospede").value("Fulano"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantidadePessoas").value(2));
    }
}
