package org.example.restaurant.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Chef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialty;

    @ManyToMany(mappedBy = "chefs")
    private List<Restaurant> restaurants = new ArrayList<>();

    // Transient field for form binding
    @Transient
    private List<Long> restaurantIds = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public List<Restaurant> getRestaurants() { return restaurants; }
    public void setRestaurants(List<Restaurant> restaurants) { this.restaurants = restaurants; }

    public List<Long> getRestaurantIds() { return restaurantIds; }
    public void setRestaurantIds(List<Long> restaurantIds) { this.restaurantIds = restaurantIds; }
}
