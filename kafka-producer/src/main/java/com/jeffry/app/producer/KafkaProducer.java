package com.jeffry.app.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeffry.app.configurations.GeneralConfig;
import com.jeffry.app.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducer {

    private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private GeneralConfig config;
    ObjectMapper objectMapper = new ObjectMapper();

    public KafkaProducer(KafkaTemplate kafkaTemplate, GeneralConfig config) {
        this.kafkaTemplate = kafkaTemplate;
        this.config = config;
    }

    @Scheduled(fixedRate = 1500)
    public void sendMessage(String key, Employee employee) throws JsonProcessingException {
        logger.info("Sending message: {}: ", employee);
        String empString = objectMapper.writeValueAsString(employee);
        kafkaTemplate.send(config.getTopic(), key, empString);
    }
}
