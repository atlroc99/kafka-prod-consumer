package com.jeffry.app.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeffry.app.entity.Commodity;
import com.jeffry.app.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "working-with-json", groupId = "")
    public void consume(String msg) throws JsonProcessingException {
        logger.info("Employee Details");
        logger.info("Consuming message: {}", msg);
        Employee employee = objectMapper.readValue(msg, Employee.class);

        logger.info("msg no: {}", employee.getCounter());
        logger.info("FirstName: {}",  employee.getFirstName());
        logger.info("LastName: {}",  employee.getLastName());
        logger.info("Address: {}",  employee.getAddress());
        logger.info("Created On: {}",  employee.getCreatedOn());
        if (employee.getLastModified() != null) {
            logger.info("Last Modified on: {}",  employee.getLastModified());
        }
    }

    //topic with two partitions
    @KafkaListener(topics = "t_commodity", groupId = "cg-dashboard")
    public void dashboardCG(String msg) throws JsonProcessingException {
        logger.info("Received msg GroupID: cg-dashboard {}: ", msg);
        Commodity commodity = objectMapper.readValue(msg, Commodity.class);
        logger.info("Commodity details - {}", "cg-notification");
        logger.info("ID: {}", commodity.getID());
        logger.info("Name: {}", commodity.getName());
        logger.info("Price: {}", commodity.getPrice());
        logger.info("Qty: {}", commodity.getQuantity());
        logger.info("Created_on: {}", commodity.getCreatedOn());
    }

    @KafkaListener(topics = "t_commodity", groupId = "cg-notification")
    public void notificationCG(String msg) throws JsonProcessingException {
        logger.info("Received msg GroupID: cg-notification {}: ", msg);
        Commodity commodity = objectMapper.readValue(msg, Commodity.class);
        logger.info("Commodity details - {}", "cg-notification");
        logger.info("ID: {}", commodity.getID());
        logger.info("Name: {}", commodity.getName());
        logger.info("Price: {}", commodity.getPrice());
        logger.info("Qty: {}", commodity.getQuantity());
        logger.info("Created_on: {}", commodity.getCreatedOn());
    }

}