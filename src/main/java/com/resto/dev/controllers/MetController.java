package com.resto.dev.controllers;

import com.resto.dev.dto.PlatRequest;
import com.resto.dev.models.Plat;
import com.resto.dev.services.DessertService;
import com.resto.dev.services.PlatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

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
public String getShowPlat(@PathVariable("id") Long id, Model model) {
    model.addAttribute("plats", platservice.getPlatById(id));
    return "plats/show";
}

    // Add new Ingredient
    @GetMapping("/plats/add")
    public String newPlat(Long id, Model model) {
        PlatRequest plat = new PlatRequest();
        model.addAttribute("plats", plat);
        return "plats/platform";
    }

    // save Ingredient
    @PostMapping("/plats/{id}")
    public String saveOrUpdate(@Valid @ModelAttribute("plat")PlatRequest plat, BindingResult bindingResult , Long id, Model model) {
        // if errors of validation return to form

         platservice.savePlat(plat);


        return "redirect:/plats";
    }

}
