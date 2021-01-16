package com.resto.dev.repos;

import com.resto.dev.models.Dessert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DessertRepository extends JpaRepository<Dessert, Long> {
}
