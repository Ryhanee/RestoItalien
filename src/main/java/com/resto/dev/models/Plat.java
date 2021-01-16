package com.resto.dev.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Plat extends Met {


    @ManyToMany(mappedBy = "plats")
    private Set<Ticket> ticketPlat = new HashSet<>();


    }

