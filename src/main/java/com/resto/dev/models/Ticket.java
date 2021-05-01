package com.resto.dev.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    private LocalDateTime date;
    private Integer nbCouvert;
    private float addition;

    @ManyToMany
    Set<Plat> plats;

    @ManyToMany
    Set<Dessert> dessert;

    @ManyToMany
    Set<Entree> entree;

    @ManyToOne
    private Tablee table;

    @ManyToOne
    private Clients clients;


}
