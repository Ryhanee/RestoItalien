package com.resto.dev.services;

import com.resto.dev.dto.PlatRequest;
import com.resto.dev.models.Plat;
import com.resto.dev.models.Ticket;

import java.time.LocalDateTime;
import java.util.List;

public interface PlatService {
    List<Plat> getPlats();
    Plat getPlatById(Long id);

    void deleteById(Long id);
    PlatRequest savePlat(PlatRequest request);
    PlatRequest findPlatRequestById(Long id);
    PlatRequest mostBuyedPlat(LocalDateTime begin, LocalDateTime end);
}
