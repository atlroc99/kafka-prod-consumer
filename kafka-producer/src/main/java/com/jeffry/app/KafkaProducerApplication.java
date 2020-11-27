package com.jeffry.app;

import com.jeffry.app.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaProducerApplication implements CommandLineRunner {

	private final KafkaProducer kafkaProducer;

	public KafkaProducerApplication(KafkaProducer kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for(int i = 0; i < 50; i++) {
			String msgKey = "emp-" + (i % 4);
			String msg = "message : " + (i*4) + "with key: " + msgKey;
			Thread.sleep(1500);
			kafkaProducer.sendMessage(msgKey, msg);
		}
	}
}
