package com.example.rest.app.model;


import com.example.rest.app.enums.TicketStatus;
import com.example.rest.app.enums.TicketType;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_type")
    private TicketType ticketType;

    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_status",nullable = true)
    private TicketStatus ticketStatus;

    public Ticket(){}

    public Ticket(Long id, TicketType ticketType, TicketStatus ticketStatus) {
        this.id = id;
        this.ticketType = ticketType;
        this.ticketStatus = ticketStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) &&
                ticketType == ticket.ticketType &&
                ticketStatus == ticket.ticketStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticketType, ticketStatus);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", ticketType=" + ticketType +
                ", ticketStatus=" + ticketStatus +
                '}';
    }
}
