package org.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.restaurant.model.Chef;

public interface ChefRepository extends JpaRepository<Chef, Long> {
}
