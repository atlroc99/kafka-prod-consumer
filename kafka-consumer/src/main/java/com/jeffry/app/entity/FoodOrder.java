package com.jeffry.app.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jeffry.app.json.CustomDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodOrder {
    private String name;
    private int amount;

    @JsonProperty("order-date")
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private LocalDate orderDate;
}
