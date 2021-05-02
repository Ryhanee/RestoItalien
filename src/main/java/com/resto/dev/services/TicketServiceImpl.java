package com.resto.dev.services;

import com.resto.dev.dto.PlatRequest;
import com.resto.dev.dto.TableeRequest;
import com.resto.dev.dto.TicketRequest;
import com.resto.dev.models.Clients;
import com.resto.dev.models.Plat;
import com.resto.dev.models.Tablee;
import com.resto.dev.models.Ticket;
import com.resto.dev.repos.ClientRepository;
import com.resto.dev.repos.PlatRepository;
import com.resto.dev.repos.TableeRepository;
import com.resto.dev.repos.TicketRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {


    private TicketRepository ticketRepos;
    private TableeRepository repotable;
    private ClientRepository repoClients;
    private PlatRepository platRepos;
    private ClientRepository clientRepository;
    private ModelMapper mapper = new ModelMapper();

    @Override
    public List<Ticket> getTickets() {
        return ticketRepos.findAll();
    }



  /*  @Autowired
    public TicketService(TicketRepository repoticket, TableeRepository repotable, ClientsRepository repoClients,
                         PlatRepository platRepos) {
        super();
        this.repoticket = repoticket;
        this.repotable = repotable;
        this.repoClients = repoClients;
        this.platRepos = platRepos;
    }*/
  public Ticket getTicketById(Long id) {
      return ticketRepos.findById(id)
              .orElseThrow(()-> new NoSuchElementException("Ticket with this id is not found"));
  }

    public TicketRequest findTicketRequestById(Long id) {
        Ticket pl =ticketRepos.findById(id)
                .orElseThrow(()-> new NoSuchElementException("no ticket with this id"));
        TicketRequest req = mapper.map(pl, TicketRequest.class);
        return req;
    }


    public Ticket addTicket(TicketRequest request) {
        Instant now = Instant.now();
     //   ticket.setDate(now);
        Ticket ticketEntity = mapper.map(request, Ticket.class);
        return ticketRepos.save(ticketEntity);
    }

    public Ticket updateTicket(Long id, Ticket newTicket) {
        Ticket met= this.getTicketById(id);
        Instant now = Instant.now();
        if(newTicket.getNumero() != 0)
            met.setNumero(newTicket.getNumero());
      //  oldmet.setDate(now);
        return ticketRepos.save(met);
    }

    public Ticket deleteticket(Long id) {
        Ticket ticket = this.getTicketById(id);
        ticketRepos.deleteById(id);
        return ticket;
    }


    public String RevenueMSD(){
        List<Ticket> tickets=ticketRepos.findAll();
        double revenueJours=0,revenueSemaine=0,revenuemois=0;
        for (Ticket ticket:tickets){
            if (ticket.getDate().isAfter(ChronoLocalDateTime.from(Instant.now().minus(Period.ofDays(30))))){
                revenuemois=revenuemois+ticket.getAddition();
            }
            if (ticket.getDate().isAfter(ChronoLocalDateTime.from(Instant.now().minus(Period.ofDays(7))))){
                revenueSemaine=revenueSemaine+ticket.getAddition();
            }
            if (ticket.getDate().isAfter(ChronoLocalDateTime.from(Instant.now().minus(Period.ofDays(1))))){
                revenueJours=revenueJours+ticket.getAddition();
            }
        }

        return "Revenue moins derniere :"+revenuemois+"\n Revenue semaine derniere :"+revenueSemaine+"\n Revenue jour derniere :"+revenueJours;
    }

    public LocalDateTime mostReservedDay(long id) {
        Optional  <Clients> Clients=clientRepository.findById(id);
        LocalDateTime dateplusrepter= LocalDateTime.from(Instant.now());
        if(Clients.isPresent()){
            dateplusrepter= Clients.get().getTickets().stream().map(ticket->ticket.getDate())
                    .collect(Collectors.groupingBy(I->I, Collectors.counting()))
                    .entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
        }else throw new NoSuchElementException("Clients id est incorrect ");
        return dateplusrepter;
    }


    public double revenuDansPeriode(LocalDateTime debutperiode, LocalDateTime finperiode) {
        List<Ticket> tickets=ticketRepos.findAll();
        double somme=0;
        for(Ticket ticket:tickets){
            if(ticket.getDate().isAfter(debutperiode)&&ticket.getDate().isBefore(finperiode)){
                somme=ticket.getAddition()+somme;
            }
        }
        return somme;
    }


    public Clients ClientsplusFidel(LocalDateTime debutperiode, LocalDateTime finperiode){
        List<Ticket>tickets=ticketRepos.findAll();
        List<Ticket>tikets=new ArrayList<>();

        for(Ticket ticket:tickets){
            if(ticket.getDate().isAfter(debutperiode)&&ticket.getDate().isBefore(finperiode)){
                tikets.add(ticket);
            }
        }
        List<Clients> cl=  tikets.stream().map(tic->tic.getClients()).collect(Collectors.toList());

        Clients fidele=cl.stream().collect(Collectors.groupingBy(l->l, Collectors.counting())).entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
        return fidele;
    }

}
