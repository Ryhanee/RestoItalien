package com.resto.dev.services;

import com.resto.dev.models.Plat;
import com.resto.dev.models.Ticket;
import com.resto.dev.repos.PlatRepository;
import com.resto.dev.repos.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {


    private TicketRepository ticketRepos;

    @Override
    public List<Ticket> getTickets() {
        return ticketRepos.findAll();
    }
}
