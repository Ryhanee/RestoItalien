package com.resto.dev.services;

import com.resto.dev.dto.PlatRequest;
import com.resto.dev.models.Met;
import com.resto.dev.models.Plat;
import com.resto.dev.repos.PlatRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class PlatServiceImpl implements PlatService {

    private PlatRepository platRepos;
    private ModelMapper mapper;

    @Override
    public List<Plat> getPlats() {
        return platRepos.findAll();
    }

    @Override
    public Plat getPlatById(Long id) {

        return platRepos.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Plat with this id is not found"));
    }
    @Override
    public void deleteById(Long id) {

        platRepos.deleteById(id);

    }
    @Override
    public PlatRequest savePlat(PlatRequest request) {
        // save
        Plat platEntity = mapper.map(request, Plat.class);

        platRepos.save(platEntity);

        // update
        return null;
    }
    @Override
    public PlatRequest findPlatRequestById(Long id) {
        Plat pl =platRepos.findById(id)
                .orElseThrow(()-> new NoSuchElementException("no plat with this id"));

        PlatRequest req = mapper.map(pl, PlatRequest.class);
        return req;
    }

}
