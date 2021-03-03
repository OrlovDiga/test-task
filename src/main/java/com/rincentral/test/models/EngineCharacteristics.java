package com.rincentral.test.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class EngineCharacteristics {

    @JsonProperty("type")
    private String type;

    /*@JsonProperty("engine_cylinders")
    private String engineCylinders;*/

    @JsonProperty("fuel_type")
    private String fuelType;

    @JsonProperty("displacement")
    private Double displacement;

    @JsonProperty("horsepower")
    private Integer horsepower;
}
