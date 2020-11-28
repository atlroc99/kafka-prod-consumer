package com.jeffry.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jeffry.app.service.FoodOderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/foodOrder")
public class FoodOrderController {

    private final FoodOderService foodOderService;

    public FoodOrderController(FoodOderService foodOderService) {
        this.foodOderService = foodOderService;
    }

    @GetMapping("/placeOrder")
    public void orderFood() {
        foodOderService.orderFood();
    }
}
