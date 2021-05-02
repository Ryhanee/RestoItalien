package com.resto.dev.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;

public class ClientRequest {

    private Long id;
    @NotBlank
    @Size(min = 3, max = 100)
    private String nom;
    @NotBlank
    @Size(min = 3, max = 100)
    private String prenom;

    private Date dateDeNaissance;
    @Positive
    private String telephone;

    private TicketRequest tickets;
}
