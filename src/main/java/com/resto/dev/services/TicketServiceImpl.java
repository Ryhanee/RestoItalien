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
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {


    private TicketRepository ticketRepos;
    private TableeRepository repotable;
    private ClientRepository repoClients;
    private PlatRepository platRepos;
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

 /*   public TableeRequest mostReservedTable(){
        Map<Long,Integer> listTableWithkey=new HashMap<>();
        List<Tablee> tables=repotable.findAll();
        for(Tablee table:tables){
            listTableWithkey.put(table.getIdtable(),table.getTicket().size());
        }
        Long toptable= listTableWithkey.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();

        Tablee table=repotable.findById(toptable).get();
        return mapper.map(table,TableeRequest.class);
    }
    public String RevenuejSm(){
        List<Ticket> tickets=repoticket.findAll();
        double revenueJours=0,revenueSemaine=0,revenuemois=0;
        for (Ticket ticket:tickets){
            if (ticket.getDate().isAfter(Instant.now().minus(Period.ofDays(30)))){
                revenuemois=revenuemois+ticket.getAddition();
            }
            if (ticket.getDate().isAfter(Instant.now().minus(Period.ofDays(7)))){
                revenueSemaine=revenueSemaine+ticket.getAddition();
            }
            if (ticket.getDate().isAfter(Instant.now().minus(Period.ofDays(1)))){
                revenueJours=revenueJours+ticket.getAddition();
            }
        }

        return "Revenue moins derniere :"+revenuemois+"\n Revenue semaine derniere :"+revenueSemaine+"\n Revenue jour derniere :"+revenueJours;
    }

    public Instant mostresrvedday(int id) {
        Optional  <Clients> Clients=repoClients.findById(id);
        Instant dateplusrepter=Instant.now();
        if(Clients.isPresent()){
            dateplusrepter= Clients.get().getTicket().stream().map(ticket->ticket.getDate())
                    .collect(Collectors.groupingBy(I->I, Collectors.counting()))
                    .entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
        }else throw new NoSuchElementException("Clients id est incorrect ");
        return dateplusrepter;
    }


    public double revenudansperiode(Instant debutperiode, Instant finperiode) {
        List<Ticket> tickets=repoticket.findAll();
        double somme=0;
        for(Ticket ticket:tickets){
            if(ticket.getDate().isAfter(debutperiode)&&ticket.getDate().isBefore(finperiode)){
                somme=ticket.getAddition()+somme;
            }
        }
        return somme;
    }
    public Clients ClientsplusFidel(Instant debutperiode, Instant finperiode){
        List<Ticket>tickets=repoticket.findAll();
        List<Ticket>ticketss=new ArrayList<>();

        for(Ticket ticket:tickets){
            if(ticket.getDate().isAfter(debutperiode)&&ticket.getDate().isBefore(finperiode)){
                ticketss.add(ticket);
            }
        }
        List<Clients> cl=  ticketss.stream().map(tic->tic.getClients()).collect(Collectors.toList());

        Clients fidel=cl.stream().collect(Collectors.groupingBy(l->l, Collectors.counting())).entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
        return fidel;
    }
/*    public MetReponse mostBuyedPlat(Instant begin,Instant end){
        List<Ticket> tickets=repoticket.findAll();
        List<Long> idList=new ArrayList<>();
        for (Ticket ticket:tickets){
            //check if ticket is in the given time interval
            if(ticket.getDate().isAfter(begin)&&ticket.getDate().isBefore(end)){

                for (MetEntity met:ticket.getMet()){
                    //filtering Plat out from list of mets
                    if(met instanceof de.tekup.project.Modele.Plat){
                        idList.add(met.getIdmet());
                    }
                }
            }
        }
        Long metid= idList.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
        MetEntity met=repomet.findById(metid).get();
        return mapper.map(met,MetReponse.class);
    }*/
}
