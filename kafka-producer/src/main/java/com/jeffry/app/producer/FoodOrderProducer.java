package com.jeffry.app.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeffry.app.configurations.GeneralConfig;
import com.jeffry.app.entity.FoodOrder;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class FoodOrderProducer {
    Logger logger = LoggerFactory.getLogger(FoodOrderProducer.class);

    private KafkaTemplate<String, String> kafkaTemplate;
    private GeneralConfig config;
    private ObjectMapper objectMapper = new ObjectMapper();

    public FoodOrderProducer(KafkaTemplate<String, String> kafkaTemplate, GeneralConfig config) {
        this.kafkaTemplate = kafkaTemplate;
        this.config = config;
    }

    public void sendMessage() {
        String chicken = null;
        String fish = null;
        String pizza = null;

        try {
            chicken = objectMapper.writeValueAsString(FoodOrder.builder().name("Chicken").orderDate(LocalDate.now(ZoneId.systemDefault())).amount(5).build());
            fish = objectMapper.writeValueAsString(FoodOrder.builder().name("Fish").amount(10).orderDate(LocalDate.now(ZoneId.systemDefault())).build());
            pizza = objectMapper.writeValueAsString(FoodOrder.builder().name("Pizza").amount(7).orderDate(LocalDate.now(ZoneId.systemDefault())).build());
        } catch (JsonProcessingException jpe) {
            logger.error(jpe.getMessage(), jpe);
        }

        if (Objects.isNull(chicken) || Objects.isNull(fish) || Objects.isNull(pizza)) {
            return;
        }

        Stream.of(chicken, fish, pizza)
                .forEach(foodOrder -> kafkaTemplate.send(config.getTopic(), foodOrder));
    }

}
