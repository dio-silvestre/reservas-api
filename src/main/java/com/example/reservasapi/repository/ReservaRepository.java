package com.example.reservasapi.repository;

import com.example.reservasapi.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    @Query("SELECT r FROM Reserva r WHERE r.status <> CANCELADA ORDER BY r.dataInicio ASC")
    List<Reserva> findAllNotCanceled();

    @Query("SELECT r FROM Reserva r WHERE r.dataInicio = ?1")
    Reserva findByDate(Date date);

    @Query("SELECT r FROM Reserva r WHERE ?1 BETWEEN r.dataInicio AND r.dataFim")
    Reserva findByDateIfBetweenDates(Date date);

}
