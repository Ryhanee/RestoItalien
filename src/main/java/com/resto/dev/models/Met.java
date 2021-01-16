package com.resto.dev.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Met {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String nom;
    private double prix;


}
