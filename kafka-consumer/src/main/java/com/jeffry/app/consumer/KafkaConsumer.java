package com.jeffry.app.consumer;

import com.jeffry.app.configurations.GeneralConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "first-topic" )
    public void consume(String msg) {
        logger.info("Consuming message: {}", msg);
    }
}
