package com.resto.dev.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Tablee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    private Integer nbCouvert;
    private String type;
    private double supplement;

    @OneToMany(mappedBy = "table")
    private List<Ticket> ticketTable = new ArrayList<>();


}
