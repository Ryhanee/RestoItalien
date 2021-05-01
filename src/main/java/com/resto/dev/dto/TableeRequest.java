package com.resto.dev.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TableeRequest {

    private Long numero;
    @Positive
    private Integer nbCouvert;
    @NotBlank
    @Size(min = 3)
    private String type;
    @Positive
    private double supplement;
    private TicketRequest ticketTable;
}
