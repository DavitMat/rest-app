package com.example.rest.app.repository;

import com.example.rest.app.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket,Long> {

    Optional<Ticket> findById(Long id);
}