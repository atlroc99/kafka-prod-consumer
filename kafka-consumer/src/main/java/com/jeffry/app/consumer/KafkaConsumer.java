package com.jeffry.app.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeffry.app.entity.FoodOrder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    ObjectMapper objectMapper = new ObjectMapper();
    private final int MAX_ITEM_COUNT = 7;

    /*
    * this consumer will throw error
    * */
    @KafkaListener(topics = "t_foodOrder", errorHandler = "foodOrderErrorHandler")
    public void consumeFoodOrder(String foodOrderMsg) throws JsonProcessingException {
        logger.info("Received MSG Food order {}", foodOrderMsg);
        FoodOrder foodOrder = objectMapper.readValue(foodOrderMsg, FoodOrder.class);

        if (foodOrder.getAmount() > MAX_ITEM_COUNT) {
            throw new IllegalArgumentException("Too many Food order. Allowed Max Item: " + MAX_ITEM_COUNT);
        }
        logger.info("food order received: {} Item: {}", foodOrder.getName(), foodOrder.getAmount());
    }
}