package com.example.reservasapi.domain;

import com.example.reservasapi.domain.enums.ReservaStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Reserva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomeHospede;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
    private Date dataInicio;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
    private Date dataFim;

    @Min(1)
    private Integer quantidadePessoas;

    private ReservaStatus status = ReservaStatus.CONFIRMADA;

    public Reserva() {
    }

    public Reserva(Integer id, String nomeHospede, Date dataInicio, Date dataFim, Integer quantidadePessoas, ReservaStatus status) {
        this.id = id;
        this.nomeHospede = nomeHospede;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.quantidadePessoas = quantidadePessoas;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeHospede() {
        return nomeHospede;
    }

    public void setNomeHospede(String nomeHospede) {
        this.nomeHospede = nomeHospede;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(Integer quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }

    public ReservaStatus getStatus() {
        return status;
    }

    public void setStatus(ReservaStatus status) {
        this.status = status;
    }
}
