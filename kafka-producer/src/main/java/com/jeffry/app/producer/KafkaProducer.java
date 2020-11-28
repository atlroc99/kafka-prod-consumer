package com.jeffry.app.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeffry.app.configurations.GeneralConfig;
import com.jeffry.app.entity.Commodity;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class KafkaProducer {
    private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    final private KafkaTemplate<String, String> kafkaTemplate;
    final private GeneralConfig config;
    final private ObjectMapper objectMapper;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate, GeneralConfig config, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.config = config;
        this.objectMapper = objectMapper;
    }

    public void send(Commodity commodity) {
        String commodityAsString = null;
        try{
             commodityAsString = objectMapper.writeValueAsString(commodity);
        }catch (Exception exception) {
            exception.printStackTrace(System.err);
        }

        if (Objects.isNull(commodityAsString)) {
            return;
        }

        logger.info("Commodity sending: {}", commodityAsString);
        kafkaTemplate.send(config.getTopic(), commodityAsString);
    }
}
