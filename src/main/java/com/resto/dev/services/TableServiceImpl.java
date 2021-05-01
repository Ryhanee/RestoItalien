package com.resto.dev.services;

import com.resto.dev.dto.TableeRequest;
import com.resto.dev.models.Tablee;
import com.resto.dev.repos.TableeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
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

}
