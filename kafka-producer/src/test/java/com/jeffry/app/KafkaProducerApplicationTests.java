package com.jeffry.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Set;

//@SpringBootTest
class KafkaProducerApplicationTests {

	@Test
	void contextLoads() {
	}


	@Test
	public void testZoneId() {
		System.out.println("Available ZoneIds: ");
		ZoneId.getAvailableZoneIds().stream().forEach(zi -> System.out.print(zi + " | "));
		System.out.println("\n");
		System.out.println("System default Zone "  + ZoneId.systemDefault().getId());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = LocalDate.now(ZoneId.systemDefault()).format(formatter);
		System.out.println("Formatted Date: " + formattedDate);
	}

}
