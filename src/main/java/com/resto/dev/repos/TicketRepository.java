package com.resto.dev.repos;

import com.resto.dev.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket , Long> {
}
