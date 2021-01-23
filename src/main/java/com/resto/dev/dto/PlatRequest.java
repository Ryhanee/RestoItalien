package com.resto.dev.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PlatRequest {
    private Long id;
    @NotBlank
    @Size(min = 3, max = 100)
    private String nom;
    @Positive
    private Double prix;



}
