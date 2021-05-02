package com.resto.dev.services;

import com.resto.dev.dto.PlatRequest;
import com.resto.dev.dto.TableeRequest;
import com.resto.dev.models.Plat;
import com.resto.dev.models.Tablee;
import com.resto.dev.repos.TableeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TableServiceImpl implements TableService {

    private TableeRepository tableRepos;
    private ModelMapper mapper;


    @Override
    public List<TableeRequest> listTables() {

        return tableRepos.findAll().stream()
                .map(u -> mapper.map(u, TableeRequest.class))
                .collect(Collectors.toList());
    }

    public List<Tablee> getAllTables(){
        return tableRepos.findAll();
    }
    public TableeRequest findTableRequestById(Long id) {
        Tablee tab =tableRepos.findById(id)
                .orElseThrow(()-> new NoSuchElementException("no table with this id"));

        TableeRequest req = mapper.map(tab, TableeRequest.class);
        return req;
    }

    public TableeRequest saveTable(TableeRequest request) {
        // save
        Tablee tab = mapper.map(request, Tablee.class);

        tableRepos.save(tab);

        // update
        return null;
    }
    @Override
    public void deleteById(Long id) {

        tableRepos.deleteById(id);

    }
}
