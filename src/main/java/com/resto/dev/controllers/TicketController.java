package com.resto.dev.controllers;


import com.resto.dev.dto.PlatRequest;
import com.resto.dev.dto.TableeRequest;
import com.resto.dev.dto.TicketRequest;
import com.resto.dev.models.Clients;
import com.resto.dev.models.Plat;
import com.resto.dev.models.Ticket;
import com.resto.dev.services.ClientService;
import com.resto.dev.services.PlatService;
import com.resto.dev.services.TableService;
import com.resto.dev.services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@AllArgsConstructor
public class TicketController {

    private TicketService serviceticket;
    private TableService tableService;
    private PlatService platService;
    private ClientService clientService;

    @GetMapping
    public List<Ticket> getAll(){
        return serviceticket.getTickets();
    }

    @GetMapping("/tickets/{id}")
    public String getTicketById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("tickets", serviceticket.getTicketById(id));
        return "tickets/show";
    }

    @GetMapping({"/tickets"})
    public String getTables(Model model) {
        model.addAttribute("tickets", serviceticket.getTickets());
        return "tickets/index";
    }
    // Add new ticket
    @GetMapping("/ticket/add")
    public String newTicket(Long numero, Model model) {
        TicketRequest ticket = new TicketRequest();
        ticket.setTable(new TableeRequest());
        model.addAttribute("tickets", ticket);
        model.addAttribute("tableList", tableService.listTables());
        model.addAttribute("platsList", platService.getPlats());
        return "tickets/ticketForm";
    }

    @PostMapping("/tickets/{id}")
    public String saveOrUpdate(@Valid @ModelAttribute("ticket")TicketRequest ticket, BindingResult bindingResult , @PathVariable("id") Long numero, Model model) {
        // if errors of validation return to form
        if (bindingResult.hasErrors()) {
            model.addAttribute("tickets", ticket);
            model.addAttribute("tableList", tableService.listTables());
            model.addAttribute("platsList", platService.getPlats());
            model.addAttribute("clients", clientService.getClients());

            return "tickets/ticketForm";
        }
        serviceticket.addTicket(ticket);
        return "redirect:/tickets/{id}/show";
    }

    @PutMapping("/tickets/{id}")
    public Ticket updateticket(@PathVariable("id") Long id, @RequestBody Ticket newticket) {
        return serviceticket.updateTicket(id, newticket);
    }
    @DeleteMapping("/tickets/{id}")
    public Ticket deleteById(@PathVariable("id") Long id) {
        return serviceticket.deleteticket(id);
    }

    @GetMapping("/revenue/period/{begin}/{end}")
    public Double RevenuePeriod(@PathVariable("begin") LocalDateTime begin, @PathVariable("end") LocalDateTime end){
        return serviceticket.revenuDansPeriode(begin, end);
    }
    @GetMapping("/client/fidel/{begin}/{end}")
    public Clients ClientplusFidel(@PathVariable("begin") LocalDateTime begin, @PathVariable("end") LocalDateTime end){
        return serviceticket.ClientsplusFidel(begin, end);
    }

    @GetMapping("/top/plat/{begin}/{end}")
    public PlatRequest getTopPlat(@PathVariable("begin") LocalDateTime begin, @PathVariable("end") LocalDateTime end){
        return platService.mostBuyedPlat(begin,end);
    }


}
