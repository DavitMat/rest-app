package com.example.rest.app.service.impl;

import com.example.rest.app.model.Ticket;
import com.example.rest.app.repository.TicketRepository;
import com.example.rest.app.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public List<Ticket> updateTicket(List<Ticket>tickets){
        return ticketRepository.saveAll(tickets);
    }

    @Override
    public List<Ticket> findAll() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets;
    }

    @Override
    public Ticket getId(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }
}
