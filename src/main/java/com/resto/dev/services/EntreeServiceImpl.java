package com.resto.dev.services;

import com.resto.dev.dto.EntreeRequest;
import com.resto.dev.dto.PlatRequest;
import com.resto.dev.models.Entree;
import com.resto.dev.models.Plat;
import com.resto.dev.repos.EntreeRepository;
import com.resto.dev.repos.PlatRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EntreeServiceImpl implements EntreeService{

    private EntreeRepository entreeRepos;

    private ModelMapper mapper;

    @Override
    public List<Entree> getEntree() {
        return entreeRepos.findAll();
    }

    @Override
    public Entree getEntreeById(Long id) {

        return entreeRepos.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Entree with this id is not found"));    }

    @Override
    public void deleteById(Long id) {
        entreeRepos.deleteById(id);

    }

    @Override
    public EntreeRequest savePlat(EntreeRequest request) {
        // save
        Entree entreeEntity = mapper.map(request, Entree.class);

         entreeRepos.save(entreeEntity);
         return null;
    }

    @Override
    public EntreeRequest findPlatRequestById(Long id) {
       Entree pl =entreeRepos.findById(id)
                .orElseThrow(()-> new NoSuchElementException("no entree with this id"));

        EntreeRequest req = mapper.map(pl, EntreeRequest.class);
        return req;    }
}
