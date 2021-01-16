package com.resto.dev.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Dessert extends Met{

    @ManyToMany(mappedBy = "dessert")
    private Set<Ticket> ticketDessert = new HashSet<>();
}


