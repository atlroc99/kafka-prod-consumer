package com.jeffry.app.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jeffry.app.josn.CustomLocalDateSerializer;
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
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate orderDate;
}
