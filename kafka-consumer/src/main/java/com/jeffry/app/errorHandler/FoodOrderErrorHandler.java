package com.jeffry.app.errorHandler;

import org.apache.kafka.clients.consumer.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/*
* Error Handler class to handle consumer error on the food order
* */
@Service("foodOrderErrorHandler")
public class FoodOrderErrorHandler implements ConsumerAwareListenerErrorHandler {

    Logger logger = LoggerFactory.getLogger(FoodOrderErrorHandler.class);

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
        logger.warn("Error occurred {}: becasue {}", message.getPayload(), exception.getMessage());
        return null;
    }
}
