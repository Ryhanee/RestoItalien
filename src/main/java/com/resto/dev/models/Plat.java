package com.resto.dev.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(of = "id")
public class Plat extends Met {


    @ManyToMany(mappedBy = "plats")
    private Set<Ticket> ticketPlat = new HashSet<>();

    public Plat(Long id, String nom, double prix, Set<Ticket> ticketPlat) {
        super(id, nom, prix);
        this.ticketPlat = ticketPlat;
    }

    public Plat() {

    }
}

