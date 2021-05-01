package com.resto.dev.services;


import com.resto.dev.dto.DessertRequest;
import com.resto.dev.dto.PlatRequest;
import com.resto.dev.models.Dessert;
import com.resto.dev.models.Plat;
import com.resto.dev.repos.DessertRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class DessertServiceImpl implements DessertService {

private DessertRepository dessertRepos;
    private ModelMapper mapper;

    @Override
    public DessertRequest saveDessert(DessertRequest request) {
        return null;
    }

    @Override
    public List<Dessert> getDesserts() {
        return dessertRepos.findAll();    }

    @Override
    public Dessert getDessertById(Long id) {
        return dessertRepos.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Dessert with this id is not found"));
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public DessertRequest findDessertRequestById(Long id ) {
        Dessert ds =dessertRepos.findById(id)
                .orElseThrow(()-> new NoSuchElementException("no dessert with this id"));

        DessertRequest req = mapper.map(ds, DessertRequest.class);
        return req;
    }
}
