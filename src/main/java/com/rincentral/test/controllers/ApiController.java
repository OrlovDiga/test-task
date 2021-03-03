package com.rincentral.test.controllers;

import com.rincentral.test.exceptions.NotValidParamException;
import com.rincentral.test.models.CarInfo;
import com.rincentral.test.models.params.CarRequestParameters;
import com.rincentral.test.models.params.MaxSpeedRequestParameters;
import com.rincentral.test.services.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final CarService carService;

    @GetMapping("/cars")
    public ResponseEntity<List<? extends CarInfo>> getCars(@Valid CarRequestParameters param) {
        return ResponseEntity.ok(
                carService.findCarsAllByParam(param));
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
    public ResponseEntity<Double> getMaxSpeed(@Valid MaxSpeedRequestParameters requestParameters)
            throws NotValidParamException {
        double averageSpeed = carService.findAllAverageSpeed(
                requestParameters.getBrand(),
                requestParameters.getModel());

        return (averageSpeed == -1) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(averageSpeed);
    }
}
