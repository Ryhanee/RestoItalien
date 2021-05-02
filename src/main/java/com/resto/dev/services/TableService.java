package com.resto.dev.services;

import com.resto.dev.dto.PlatRequest;
import com.resto.dev.dto.TableeRequest;
import com.resto.dev.models.Tablee;
import com.resto.dev.models.Ticket;

import java.util.List;

public interface TableService {
    List<TableeRequest> listTables();

     List<Tablee> getAllTables();
    TableeRequest findTableRequestById(Long id);
    TableeRequest saveTable(TableeRequest request);
    void deleteById(Long id);
//     <id> int getPrix();
}

