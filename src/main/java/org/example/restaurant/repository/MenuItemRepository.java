package org.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.restaurant.model.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
