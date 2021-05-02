package com.resto.dev.controllers;

import com.resto.dev.dto.DessertRequest;
import com.resto.dev.dto.EntreeRequest;
import com.resto.dev.dto.PlatRequest;
import com.resto.dev.dto.TableeRequest;
import com.resto.dev.services.TableService;
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
public class TableeController {
TableService tableService;
    @GetMapping({"/tables"})
    public String getTables(Model model) {
        model.addAttribute("tables", tableService.listTables());
        return "table/tables";
    }
    @GetMapping("/table/{id}/show")
    public String getShowTable(@PathVariable("id") Long id, Model model) {
        model.addAttribute("plats", tableService.findTableRequestById(id));
        return "table/show";
    }

    // Add new table
    @GetMapping("/table/add")
    public String newTable(Long id, Model model) {
        TableeRequest tab = new TableeRequest();
        model.addAttribute("table", tab);
        return "table/add";
    }

    // save Ingredient
    @PostMapping("/table/{id}")
    public String saveOrUpdate(@Valid @ModelAttribute("table")TableeRequest table, BindingResult bindingResult , Long id, Model model) {
        // if errors of validation return to form

        tableService.saveTable(table);
        return "redirect:/plats";
    }
    // Update Ingredient
    @GetMapping("/table/{id}/update")
    public String updateTable(@PathVariable("id") long id, Model model) {
        TableeRequest tableeRequest = tableService.findTableRequestById(id);

        model.addAttribute("table", tableeRequest);

        return "table/add";
    }
    @GetMapping("/table/{id}/delete")
    public String deleteIngredient(@PathVariable("id") long id) {
        tableService.deleteById(id);
        return "redirect:/tables";
    }


}
