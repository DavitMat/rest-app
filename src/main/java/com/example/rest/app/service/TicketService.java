package com.example.rest.app.service;

import com.example.rest.app.model.Ticket;

import java.util.List;

public interface TicketService {

    Ticket getId(Long id);

    Ticket saveTicket(Ticket ticket);

    List<Ticket> findAll();

    void deleteById(Long id);

    List<Ticket> updateTicket(List<Ticket>tickets);
}