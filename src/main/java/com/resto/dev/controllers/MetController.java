package com.resto.dev.controllers;

import com.resto.dev.models.Plat;
import com.resto.dev.services.DessertService;
import com.resto.dev.services.PlatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class MetController {

    // call service
    private PlatService platservice;
    private DessertService dessert_service;
    // to get all Recipes
/*
    // add recipes to model of index.html
    @GetMapping({"/plats"})
    public String getIndex(Model model) {
      model.addAttribute("plats", plat_service.getPlats());
        return "plats/plat";s
    }
    */
@GetMapping("/plats/{id}/show")
public String getShowPlat(@PathVariable("id") long id, Model model) {
    model.addAttribute("plats", platservice.getPlatById(id));
    return "plats/show";
}


}
