package com.jeffry.app.producer;

import com.jeffry.app.configurations.GeneralConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private GeneralConfig config;


    public KafkaProducer(KafkaTemplate kafkaTemplate, GeneralConfig config) {
        this.kafkaTemplate = kafkaTemplate;
        this.config = config;
    }

    public void sendMessage(String msg) {
        final String kafka_topic = config.getTopic();
        logger.info("Topic name {} and Message {}: ", kafka_topic, msg);
        kafkaTemplate.send(kafka_topic, msg);
    }
}
