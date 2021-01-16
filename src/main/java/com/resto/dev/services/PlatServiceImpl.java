package com.resto.dev.services;

import com.resto.dev.models.Plat;
import com.resto.dev.repos.PlatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class PlatServiceImpl implements PlatService {

    private PlatRepository platRepos;

    @Override
    public List<Plat> getPlats() {
        return platRepos.findAll();
    }

    @Override
    public Plat getPlatById(Long id) {

        return platRepos.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Plat with this id is not found"));
    }
}
