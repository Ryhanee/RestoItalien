package com.resto.dev.services;

import com.resto.dev.dto.DessertRequest;
import com.resto.dev.dto.PlatRequest;
import com.resto.dev.models.Dessert;
import com.resto.dev.models.Plat;

import java.util.List;

public interface DessertService {

    DessertRequest saveDessert(DessertRequest request);
    List<Dessert> getDesserts();
    Dessert getDessertById(Long id);

    void deleteById(Long id);
    DessertRequest findDessertRequestById(Long id);
}
