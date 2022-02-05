package com.example.rest.app.controller;

import com.example.rest.app.enums.TicketStatus;
import com.example.rest.app.model.Ticket;
import com.example.rest.app.model.User;
import com.example.rest.app.service.TicketService;
import com.example.rest.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket/")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Ticket>> allTickets() {
        List<Ticket> tickets = this.ticketService.findAll();
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Ticket> saveTicket(@RequestBody Ticket ticket) {
        this.ticketService.saveTicket(ticket);
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Ticket> deleteTicket(@PathVariable("id") Long id) {
        Ticket ticket = this.ticketService.getId(id);
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.ticketService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Ticket> getTicketId(@PathVariable("id") Long id) {

        Ticket ticket = this.ticketService.getId(id);
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket) {
        this.ticketService.saveTicket(ticket);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @RequestMapping(value = "/tickets/{user_id}", method = RequestMethod.GET)
    public ResponseEntity<List<Ticket>> listTicket(@PathVariable("user_id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = userService.getById(id);

        return new ResponseEntity<>(user.getListTicket(), HttpStatus.OK);
    }

    @RequestMapping(value = "/decide/{id}", method = RequestMethod.GET)
    public ResponseEntity<Ticket> ticketManipul(@PathVariable("id") Long id,
                                                @RequestParam TicketStatus ticketStatus) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Ticket ticket = ticketService.getId(id);
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ticket.setTicketStatus(ticketStatus);
        ticketService.saveTicket(ticket);

        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @RequestMapping(value = "/forward/{ticket_id}", method = RequestMethod.GET)
    public ResponseEntity<Ticket> forward(@PathVariable Long ticket_id,
                                          @RequestParam Long source_id,
                                          @RequestParam Long destination_id) {

        Ticket ticket = ticketService.getId(ticket_id);
        User source = userService.getById(source_id);
        source.getListTicket().remove(ticket);
        userService.saveUser(source);

        User user = userService.getById(destination_id);
        user.getListTicket().add(ticket);
        userService.saveUser(user);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
