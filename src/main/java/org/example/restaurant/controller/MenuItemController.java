package org.example.restaurant.controller;

import org.example.restaurant.model.MenuItem;
import org.example.restaurant.repository.MenuItemRepository;
import org.example.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/menuItems")
public class MenuItemController {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuItemController(MenuItemRepository menuItemRepository,
                              RestaurantRepository restaurantRepository) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public String showMenuItems(Model model) {
        model.addAttribute("menuItems", menuItemRepository.findAll());
        model.addAttribute("menuItem", new MenuItem());
        model.addAttribute("restaurants", restaurantRepository.findAll());
        return "menuItems";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute MenuItem menuItem) {
        menuItemRepository.save(menuItem);
        return "redirect:/menuItems";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        menuItemRepository.deleteById(id);
        return "redirect:/menuItems";
    }
}

