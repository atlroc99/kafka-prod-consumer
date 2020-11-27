package com.jeffry.app.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jeffry.app.josn.CustomLocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Commodity {

    private String ID;

    @JsonProperty("commodity_name")
    private String name;

    @JsonProperty("commodity_price")
    private BigDecimal price;

    private int quantity;

    @JsonProperty("created_on")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate createdOn;
}
