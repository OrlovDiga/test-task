package com.rincentral.test.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
public final class CarFullInfo extends CarInfo {

    @JsonProperty("start_year_production")
    private int startYearProd;

    @JsonProperty("end_year_production")
    private int endYearProd;

    @JsonProperty("gearbox")
    private String gearbox;

    @JsonProperty("wheel_drive")
    private String wheelDrive;

    @JsonProperty("body_car")
    private BodyCharacteristics bodyCar;

    @JsonProperty("engine_car")
    private EngineCharacteristics engineCar;

    @JsonProperty("acceleration")
    private String acceleration;

    @JsonProperty("max_speed")
    private Integer maxSpeed;

    public CarFullInfo(Integer id,
                       String segment,
                       String brand,
                       String model,
                       String country,
                       String generation,
                       String modification,
                       int startYearProd,
                       int endYearProd,
                       String gearbox,
                       String wheelDrive,
                       BodyCharacteristics bodyCar,
                       EngineCharacteristics engineCar,
                       String acceleration,
                       Integer maxSpeed) {
        super(id, segment, brand, model, country, generation, modification);
        this.id = id;
        this.segment = segment;
        this.brand = brand;
        this.model = model;
        this.country = country;
        this.generation = generation;
        this.modification = modification;
        this.startYearProd = startYearProd;
        this.endYearProd = endYearProd;
        this.gearbox = gearbox;
        this.wheelDrive = wheelDrive;
        this.bodyCar = bodyCar;
        this.engineCar = engineCar;
        this.acceleration = acceleration;
        this.maxSpeed = maxSpeed;
    }
}
