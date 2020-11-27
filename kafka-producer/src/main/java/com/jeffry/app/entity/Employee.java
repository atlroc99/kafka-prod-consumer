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
public class Employee {

    private String id;
    @JsonProperty("first-name")
    private String firstName;

    @JsonProperty("last-name")
    private String lastName;
    private String address;

    @JsonProperty("createdOn")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate createdOn;

    @JsonProperty("lastModifiedOn")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate lastModifiedOn;

    @JsonProperty("msg-counter")
    private int counter;
}
