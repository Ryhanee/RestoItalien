package com.resto.dev.services;

import com.resto.dev.dto.TableeRequest;
import com.resto.dev.models.Tablee;
import com.resto.dev.models.Ticket;

import java.util.List;

public interface TableService {
    List<TableeRequest> listTables();

     List<Tablee> getAllTables();
}
