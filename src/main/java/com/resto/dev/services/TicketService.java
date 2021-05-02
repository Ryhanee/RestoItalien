package com.resto.dev.services;

import com.resto.dev.dto.TicketRequest;
import com.resto.dev.models.Clients;
import com.resto.dev.models.Plat;
import com.resto.dev.models.Ticket;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketService {
    List<Ticket> getTickets();
    public Ticket getTicketById(Long id);
    TicketRequest findTicketRequestById(Long id);
    Ticket addTicket(TicketRequest request);
    public Ticket updateTicket(Long id, Ticket newTicket);
    Ticket deleteticket(Long id);
    Clients ClientsplusFidel(LocalDateTime debutperiode, LocalDateTime finperiode);
    double revenuDansPeriode(LocalDateTime debutperiode, LocalDateTime finperiode);
    LocalDateTime mostReservedDay(long id);
    public String RevenueMSD();
}
