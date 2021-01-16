package com.resto.dev.services;

import com.resto.dev.models.Plat;
import com.resto.dev.models.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> getTickets();
}
