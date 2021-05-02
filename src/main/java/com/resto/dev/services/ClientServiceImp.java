package com.resto.dev.services;

import com.resto.dev.models.Clients;
import com.resto.dev.repos.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImp implements ClientService {

    private ClientRepository clientRepository;
    @Override
    public List<Clients> getClients(){return  clientRepository.findAll();}
}
