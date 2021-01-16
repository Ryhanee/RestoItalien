package com.resto.dev.repos;

import com.resto.dev.models.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Clients, Long> {
}
