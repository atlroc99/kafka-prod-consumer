package com.jeffry.app.service;

import com.jeffry.app.entity.Commodity;
import com.jeffry.app.producer.KafkaProducer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@EnableScheduling
public class CommodityService {

    private RestTemplate restTemplate;
    private KafkaProducer kafkaProducer;

    public CommodityService(RestTemplateBuilder restTemplateBuilder, KafkaProducer kafkaProducer) {
        this.restTemplate = restTemplateBuilder.build();
        this.kafkaProducer = kafkaProducer;
    }

    public List<Commodity> getAllCommodities() {
        return Arrays.asList(
                Commodity.builder()
                        .ID(UUID.randomUUID().toString())
                        .name("GOLD")
                        .price(new BigDecimal(1500))
                        .createdOn(LocalDate.now())
                        .quantity(45)
                        .build(),
                Commodity.builder()
                        .ID(UUID.randomUUID().toString())
                        .name("SILVER")
                        .price(new BigDecimal(800))
                        .createdOn(LocalDate.now())
                        .quantity(35)
                        .build(),
                Commodity.builder()
                        .ID(UUID.randomUUID().toString())
                        .name("COPPER")
                        .price(new BigDecimal(600))
                        .createdOn(LocalDate.now())
                        .quantity(85)
                        .build(),
                Commodity.builder()
                        .ID(UUID.randomUUID().toString())
                        .name("IRON")
                        .price(new BigDecimal(100))
                        .createdOn(LocalDate.now())
                        .quantity(100)
                        .build(),
                Commodity.builder()
                        .ID(UUID.randomUUID().toString())
                        .name("BRASS")
                        .price(new BigDecimal(50))
                        .createdOn(LocalDate.now())
                        .quantity(1300)
                        .build()
        );
    }

    @Scheduled(fixedRate = 2000)
    public void fetchCommodities() {

        final String endpooint_url = "http://localhost:8080/api/v1/commodities/all";
        /*ResponseEntity<List<Commodity>> body = restTemplate.exchange(endpooint_url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseEntity<List<Commodity>>>() {
                }
        ).getBody();*/

        List<Commodity> commodities = restTemplate.exchange(endpooint_url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Commodity>>() {
                }
        ).getBody();

        commodities.forEach(commodity -> kafkaProducer.send(commodity));
    }

}
