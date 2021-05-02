package com.resto.dev.controllers;


import com.resto.dev.dto.PlatRequest;
import com.resto.dev.dto.TableeRequest;
import com.resto.dev.dto.TicketRequest;
import com.resto.dev.models.Plat;
import com.resto.dev.models.Ticket;
import com.resto.dev.services.PlatService;
import com.resto.dev.services.TableService;
import com.resto.dev.services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class TicketController {

    private TicketService serviceticket;
    private TableService tableService;
    private PlatService platService;

    @GetMapping
    public List<Ticket> getAll(){
        return serviceticket.getTickets();
    }

    @GetMapping("/tickets/{id}")
    public String getTicketById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("tickets", serviceticket.getTicketById(id));
        return "tickets/show";
    }

 /*   @GetMapping("/plats/{id}/show")
    public String getShowPlat(@PathVariable("id") Long id, Model model) {
        model.addAttribute("plats", platservice.getPlatById(id));
        return "plats/show";
    }

   /* @GetMapping("clientjour/{id}")
    public Instant clientjour(@PathVariable("id") Integer id) {
        return serviceticket.mostresrvedday(id);
    }
    @GetMapping("/most")
    public Tablereponse most() {
        return serviceticket.mostReservedTable();
    }
    @GetMapping("/x")
    public String Revnue() {
        return serviceticket.RevenuejSm();
    }
*/

    @GetMapping({"/tickets"})
    public String getTables(Model model) {
        model.addAttribute("tickets", serviceticket.getTickets());
        return "tickets/index";
    }
    // Add new Ingredient
    @GetMapping("/ticket/add")
    public String newTicket(Long numero, Model model) {
        TicketRequest ticket = new TicketRequest();
        ticket.setTable(new TableeRequest());
        model.addAttribute("tickets", ticket);
        model.addAttribute("tableList", tableService.listTables());
        model.addAttribute("platsList", platService.getPlats());
        return "tickets/ticketForm";
    }

    // save Ingredient
    @PostMapping("/tickets/{id}")
    public String saveOrUpdate(@Valid @ModelAttribute("ticket")TicketRequest ticket, BindingResult bindingResult , @PathVariable("id") Long numero, Model model) {
        // if errors of validation return to form
        if (bindingResult.hasErrors()) {
            model.addAttribute("tickets", ticket);
            model.addAttribute("tableList", tableService.listTables());
            model.addAttribute("platsList", platService.getPlats());
//            Plat pl = new Plat();
//            double prix =  pl.getPrix();

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

   /* @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }


    @GetMapping("/revenue/period/{begin}/{end}")
    public Double RevenuePeriod(@PathVariable("begin") Instant begin, @PathVariable("end") Instant end){
        return serviceticket.revenudansperiode(begin, end);
    }
    @GetMapping("/client/fidel/{begin}/{end}")
    public de.tekup.project.Modele.Client ClientplusFidel(@PathVariable("begin") Instant begin, @PathVariable("end") Instant end){
        return serviceticket.ClientplusFidel(begin, end);
    }

    @GetMapping("/top/plat/{begin}/{end}")
    public MetReponse getTopPlat(@PathVariable("begin") Instant begin, @PathVariable("end") Instant end){
        return serviceticket.mostBuyedPlat(begin,end);
    }
*/


}
