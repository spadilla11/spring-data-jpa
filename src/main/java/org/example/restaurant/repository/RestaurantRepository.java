package org.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.restaurant.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
