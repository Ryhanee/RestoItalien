package com.resto.dev.controllers;

import com.resto.dev.services.PlatService;
import com.resto.dev.services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class IndexController {

    // call service
    private TicketService Tservices;
    private PlatService Pservices;
    // to get all Recipes

    // add recipes to model of index.html
    @GetMapping({"/plats"})
    public String getIndex(Model model) {
        model.addAttribute("plat", Pservices.getPlats());
        return "plats/plat";
    }


}
