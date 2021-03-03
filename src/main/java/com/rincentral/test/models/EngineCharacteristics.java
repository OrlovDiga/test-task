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

    @JsonProperty("engine_type")
    private String engineType;

    /*@JsonProperty("engine_cylinders")
    private String engineCylinders;*/

    @JsonProperty("fuel_type")
    private String fuelType;

    @JsonProperty("engine_displacement")
    private Double engineDisplacement;

    @JsonProperty("engine_horsepower")
    private Integer engineHorsepower;
}
