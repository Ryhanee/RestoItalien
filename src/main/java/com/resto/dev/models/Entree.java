package com.resto.dev.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Entree extends Met{

    @ManyToMany(mappedBy = "entree")
    private Set<Ticket> ticketEntree = new HashSet<>();
}
