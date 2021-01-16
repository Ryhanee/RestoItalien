package com.resto.dev.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.OneToMany;


@Data
@Entity
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private Date dateDeNaissance;
    private String telephone;


    @OneToMany(mappedBy = "clients")
    private List<Ticket> tickets = new ArrayList<>();






}
