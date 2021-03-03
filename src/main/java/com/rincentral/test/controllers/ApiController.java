package com.rincentral.test.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rincentral.test.models.CarFullInfo;
import com.rincentral.test.models.CarInfo;
import com.rincentral.test.models.params.CarRequestParameters;
import com.rincentral.test.models.params.MaxSpeedRequestParameters;
import com.rincentral.test.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Collections.emptyList;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final CarService carService;
    private final ObjectMapper mapper;

    @GetMapping("/cars")
    public ResponseEntity<List<? extends CarInfo>> getCars(CarRequestParameters param) {
        return ResponseEntity.ok(
                carService.findCarsAllByParam(
                        param.getCountry(),
                        param.getSegment(),
                        param.getMinEngineDisplacement(),
                        param.getMinEngineHorsepower(),
                        param.getMinMaxSpeed(),
                        param.getSearch(),
                        param.getIsFull(),
                        param.getYear(),
                        param.getBodyStyle()));
    }

    @GetMapping("/fuel-types")
    public ResponseEntity<List<String>> getFuelTypes() {
        return ResponseEntity.ok(carService.findAllFuelTypes());
    }

    @GetMapping("/body-styles")
    public ResponseEntity<List<String>> getBodyStyles() {
        return ResponseEntity.ok(carService.findAllBodyStyles());
    }

    @GetMapping("/engine-types")
    public ResponseEntity<List<String>> getEngineTypes() {
        return ResponseEntity.ok(carService.findAllEngineTypes());
    }

    @GetMapping("/wheel-drives")
    public ResponseEntity<List<String>> getWheelDrives() {
        return ResponseEntity.ok(carService.findAllWheelDrives());
    }

    @GetMapping("/gearboxes")
    public ResponseEntity<List<String>> getGearboxTypes() {
        return ResponseEntity.ok(carService.findAllGearboxTypes());
    }

    @GetMapping("/max-speed")
    public ResponseEntity<Double> getMaxSpeed(MaxSpeedRequestParameters requestParameters) {
        return ResponseEntity.ok(
                carService.findAllAverageSpeed(
                        requestParameters.getBrand(),
                        requestParameters.getModel()));
    }
}
