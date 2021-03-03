package com.rincentral.test.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
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
}
