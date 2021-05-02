package com.resto.dev.services;

import com.resto.dev.dto.PlatRequest;
import com.resto.dev.models.Met;
import com.resto.dev.models.Plat;
import com.resto.dev.models.Ticket;
import com.resto.dev.repos.PlatRepository;
import com.resto.dev.repos.TicketRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlatServiceImpl implements PlatService {

    private PlatRepository platRepos;
    private TicketRepository ticketRepos;

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


        public PlatRequest mostBuyedPlat(LocalDateTime begin, LocalDateTime end){
        List<Ticket> tickets=ticketRepos.findAll();
        List<Long> idList=new ArrayList<>();
        for (Ticket ticket:tickets){
            //check if ticket is in the given time interval
            if(ticket.getDate().isAfter(begin)&&ticket.getDate().isBefore(end)){

                for (Plat plat:ticket.getPlats()){
                    //filtering Plat out from list of mets
                    if(plat instanceof Plat){
                        idList.add(plat.getId());
                    }
                }
            }
        }
        Long platid= idList.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
        Plat pl=platRepos.findById(platid).get();
        return mapper.map(pl,PlatRequest.class);
    }
}
