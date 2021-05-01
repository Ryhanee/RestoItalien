package com.resto.dev.controllers;

import com.resto.dev.dto.DessertRequest;
import com.resto.dev.dto.EntreeRequest;
import com.resto.dev.dto.PlatRequest;
import com.resto.dev.models.Plat;
import com.resto.dev.services.DessertService;
import com.resto.dev.services.EntreeService;
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
    private EntreeService entreeService;
    // to get all Recipes
    // to get all Recipes

    // add recipes to model of index.html
    @GetMapping({"/plats"})
    public String getIndex(Model model) {
        model.addAttribute("plat", platservice.getPlats());
        return "plats/plat";
    }
    @GetMapping({"/desserts"})
    public String getDesserts(Model model) {
        model.addAttribute("dessert", dessert_service.getDesserts());
        return "plats/desserts";
    }
    @GetMapping({"/entrees"})
    public String getEntree(Model model) {
        model.addAttribute("entree", entreeService.getEntree());
        return "plats/entree";
    }
@GetMapping("/plats/{id}/show")
public String getShowPlat(@PathVariable("id") Long id, Model model) {
    model.addAttribute("plats", platservice.getPlatById(id));
    return "plats/show";
}
    @GetMapping("/desserts/{id}/show")
    public String getShowDessert(@PathVariable("id") Long id, Model model) {
        model.addAttribute("plats", dessert_service.getDessertById(id));
        return "plats/showDessert";
    }
    @GetMapping("/entrees/{id}/show")
    public String getShowEntree(@PathVariable("id") Long id, Model model) {
        model.addAttribute("entree", entreeService.getEntreeById(id));
        return "plats/showEntree";
    }
    // Add new Ingredient
    @GetMapping("/plats/add")
    public String newPlat(Long id, Model model) {
        PlatRequest plat = new PlatRequest();
        DessertRequest dessert = new DessertRequest();
        EntreeRequest entree = new EntreeRequest();
        model.addAttribute("plats", plat);
        model.addAttribute("dessert",dessert);
        model.addAttribute("entree", entree);
        return "plats/platform";
    }

    // save Ingredient
    @PostMapping("/plats/{id}")
    public String saveOrUpdate(@Valid @ModelAttribute("plat")PlatRequest plat, BindingResult bindingResult , Long id, Model model) {
        // if errors of validation return to form

         platservice.savePlat(plat);
        return "redirect:/plats";
    }
    @PostMapping("/desserts/{id}")
    public String saveOrUpdate(@Valid @ModelAttribute("dessert")DessertRequest dessert, BindingResult bindingResult , Long id, Model model) {
        // if errors of validation return to form

        dessert_service.saveDessert(dessert);
        return "redirect:/desserts";
    }
    @PostMapping("/entrees/{id}")
    public String saveOrUpdate(@Valid @ModelAttribute("entree")EntreeRequest entree, BindingResult bindingResult , Long id, Model model) {
        // if errors of validation return to form
        entreeService.savePlat(entree);
        return "redirect:/entree";
    }
}
