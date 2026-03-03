package org.example.restaurant.controller;

import org.example.restaurant.model.Chef;
import org.example.restaurant.model.Restaurant;
import org.example.restaurant.repository.ChefRepository;
import org.example.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chefs")
public class ChefController {

    private final ChefRepository chefRepository;
    private final RestaurantRepository restaurantRepository;

    public ChefController(ChefRepository chefRepository,
                          RestaurantRepository restaurantRepository) {
        this.chefRepository = chefRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public String showChefs(Model model) {
        model.addAttribute("chefs", chefRepository.findAll());
        model.addAttribute("chef", new Chef());
        model.addAttribute("restaurants", restaurantRepository.findAll());
        return "chefs";
    }

    @PostMapping("/add")
    public String addChef(@ModelAttribute Chef chef) {
        List<Restaurant> selectedRestaurants =
                restaurantRepository.findAllById(chef.getRestaurantIds());
        chef.setRestaurants(selectedRestaurants);
        for (Restaurant restaurant : selectedRestaurants) {
            restaurant.getChefs().add(chef);
        }
        chefRepository.save(chef);
        return "redirect:/chefs";
    }

    @GetMapping("/delete/{id}")
    public String deleteChef(@PathVariable Long id) {
        Chef chef = chefRepository.findById(id).orElse(null);
        if (chef != null) {
            for (Restaurant restaurant : chef.getRestaurants()) {
                restaurant.getChefs().remove(chef);
            }
            chef.getRestaurants().clear();
        }
        chefRepository.deleteById(id);
        return "redirect:/chefs";
    }
}