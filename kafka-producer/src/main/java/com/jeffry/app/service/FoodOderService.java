package com.jeffry.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jeffry.app.producer.FoodOrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodOderService {

    @Autowired
    FoodOrderProducer foodOrderProducer;

    public void orderFood() {
        foodOrderProducer.sendMessage();
    }
}
