package com.resto.dev.services;

import com.resto.dev.dto.PlatRequest;
import com.resto.dev.models.Plat;

import java.util.List;

public interface PlatService {
    List<Plat> getPlats();
    Plat getPlatById(Long id);

    void deleteById(Long id);
    PlatRequest savePlat(PlatRequest request);
    PlatRequest findPlatRequestById(Long id);
}
