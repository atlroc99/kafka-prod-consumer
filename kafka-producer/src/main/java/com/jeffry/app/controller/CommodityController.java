package com.jeffry.app.controller;

import com.jeffry.app.entity.Commodity;
import com.jeffry.app.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/commodities")
public class CommodityController {

    @Autowired
    CommodityService commodityService;

    @GetMapping("/all")
    public List<Commodity> getAllCommodities() {
        return commodityService.getAllCommodities();
    }
}
