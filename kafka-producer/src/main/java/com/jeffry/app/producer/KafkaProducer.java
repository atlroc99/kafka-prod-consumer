package com.jeffry.app.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeffry.app.configurations.GeneralConfig;
import com.jeffry.app.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Service
@EnableScheduling
public class KafkaProducer {

    private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
    private int counter = 0;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private GeneralConfig config;
    ObjectMapper objectMapper = new ObjectMapper();

    public KafkaProducer(KafkaTemplate kafkaTemplate, GeneralConfig config) {
        this.kafkaTemplate = kafkaTemplate;
        this.config = config;
    }

    @Scheduled(fixedRate = 1500)
    public void sendMessage() throws JsonProcessingException {
        Employee employee = Employee.builder()
                .id(UUID.randomUUID().toString())
                .firstName("Omar")
                .lastName("Zaman: ")
                .createdOn(LocalDate.now())
                .address("Lilburn")
                .counter(counter ++)
                .build();
        logger.info("Sending message: {}: ", employee);
        String empString = objectMapper.writeValueAsString(employee);
        kafkaTemplate.send(config.getTopic(), empString);
    }
}
