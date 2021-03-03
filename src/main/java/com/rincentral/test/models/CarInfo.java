package com.rincentral.test.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CarInfo {

    @JsonProperty("id")
    protected Integer id;

    @JsonProperty("segment")
    protected String segment;

    @JsonProperty("brand")
    protected String brand;

    @JsonProperty("model")
    protected String model;

    @JsonProperty("country")
    protected String country;

    @JsonProperty("generation")
    protected String generation;

    @JsonProperty("modification")
    protected String modification;

    public CarInfo(Integer id,
                   String segment,
                   String brand,
                   String model,
                   String country,
                   String generation,
                   String modification) {
        this.id = id;
        this.segment = segment;
        this.brand = brand;
        this.model = model;
        this.country = country;
        this.generation = generation;
        this.modification = modification;
    }
}
