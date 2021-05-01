package com.resto.dev.services;

import com.resto.dev.dto.EntreeRequest;
import com.resto.dev.models.Entree;

import java.util.List;

public interface EntreeService {
    List<Entree> getEntree();
    Entree getEntreeById(Long id);

    void deleteById(Long id);
    EntreeRequest savePlat(EntreeRequest request);
    EntreeRequest findPlatRequestById(Long id);
}
