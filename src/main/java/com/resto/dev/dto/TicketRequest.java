package com.resto.dev.dto;

import com.resto.dev.models.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class TicketRequest {

private Long id;
    @CreatedDate
    private LocalDateTime date;
    @Positive
    private Integer nbCouvert;
    @Positive
    private float addition;

    @NotBlank
    @Size(min = 3, max = 100)
    private String nom;
    @Positive
    private Double prix;

    private PlatRequest plats;
    private PlatRequest dessert;
    private PlatRequest entree;
    private TableeRequest table;
    private ClientRequest clients;
}
