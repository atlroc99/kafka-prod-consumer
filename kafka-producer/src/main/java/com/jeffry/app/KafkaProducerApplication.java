package com.jeffry.app;

import com.jeffry.app.entity.Employee;
import com.jeffry.app.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.UUID;

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
		int counter = 0;
		Employee employee = Employee.builder()
				.id(UUID.randomUUID().toString())
				.firstName("Omar")
				.lastName("Zaman: ")
				.createdOn(LocalDate.now())
				.address("Lilburn")
				.counter(counter ++)
				.build();
		kafkaProducer.sendMessage("employee", employee);
	}
}
