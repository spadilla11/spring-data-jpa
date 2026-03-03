package org.example.restaurant.controller;

import org.example.restaurant.model.Restaurant;
import org.example.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;


    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public String showRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantRepository.findAll());
        model.addAttribute("restaurant", new Restaurant()); // form object
        return "restaurants";
    }

    @GetMapping("/{id}/chefs")
    public String viewChefs(@PathVariable Long id, Model model) {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        model.addAttribute("restaurant", restaurant);
        model.addAttribute("chefs", restaurant.getChefs());

        return "chefs";
    }

    @GetMapping("/{id}/menu")
    public String viewMenu(@PathVariable Long id, Model model) {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        model.addAttribute("restaurant", restaurant);
        model.addAttribute("menuItems", restaurant.getMenuItems());

        return "restaurant-menu";
    }

    @PostMapping("/add")
    public String addRestaurant(@ModelAttribute Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        return "redirect:/restaurants";
    }

    @GetMapping("/edit/{id}")
    public String editRestaurant(@PathVariable Long id, Model model) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id " + id));
        model.addAttribute("restaurant", restaurant);
        return "edit-restaurant"; // create edit-restaurant.html template
    }

    @PostMapping("/update/{id}")
    public String updateRestaurant(@PathVariable Long id, @ModelAttribute Restaurant updatedRestaurant) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id " + id));
        restaurant.setName(updatedRestaurant.getName());
        restaurant.setLocation(updatedRestaurant.getLocation());
        restaurantRepository.save(restaurant);
        return "redirect:/restaurants";
    }

    @GetMapping("/delete/{id}")
    public String deleteRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        restaurant.getMenuItems().clear();
        restaurant.getChefs().clear();
        restaurantRepository.delete(restaurant);
        return "redirect:/restaurants";
    }
}
