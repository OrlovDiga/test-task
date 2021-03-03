package com.rincentral.test.services.impl;

import com.rincentral.test.exceptions.NotValidParamException;
import com.rincentral.test.models.CarFullInfo;
import com.rincentral.test.models.CarInfo;
import com.rincentral.test.models.params.CarRequestParameters;
import com.rincentral.test.services.CarService;
import com.rincentral.test.util.CarPredicates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Orlov Diga
 */
@Slf4j
@Service("CarService")
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final List<CarFullInfo> carList;

    @Override
    public List<? extends CarInfo> findCarsAllByParam(final CarRequestParameters params) {
        Predicate<CarFullInfo> carFilter = new CarPredicates.Builder()
                .countryEq(params.getCountry())
                .segmentEq(params.getSegment())
                .containsYear(params.getYear())
                .speedMore(params.getMinMaxSpeed())
                .hrEq(params.getMinEngineHorsepower())
                .displacementEq(params.getMinEngineDisplacement())
                .bodyStyleEq(params.getBodyStyle())
                .build();

        Stream<CarFullInfo> streamCars = carList.stream().filter(carFilter);

        if (params.getSearch() != null && params.getSearch() > 0) {
            streamCars = streamCars.sorted(Comparator
                                    .comparing(CarFullInfo::getModel)
                                    .thenComparing(CarFullInfo::getGeneration)
                                    .thenComparing(CarFullInfo::getModification))
            .limit(params.getSearch());
        }

        return (params.getIsFull() != null && params.getIsFull()) ?
                streamCars.collect(Collectors.toList()) :
                streamCars.map(CarFullInfo::toCarInfo).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBodyStyles() {
         return carList.stream()
                .flatMap(it -> Arrays.stream(it.getBodyCar().getStyle().split(",")))
                .map(String::trim)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllEngineTypes() {
        return carList.stream()
                .map(it -> it.getEngineCar().getType())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllWheelDrives() {
        return carList.stream()
                .map(CarFullInfo::getWheelDrive)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllFuelTypes() {
        return carList.stream()
                .map(it -> it.getEngineCar().getFuelType())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllGearboxTypes() {
        return carList.stream()
                .map(CarFullInfo::getGearbox)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public double findAllAverageSpeed(final String brand, final String model) {
        int temp = 1;
        if (brand == null || brand.isBlank()) {
            temp *= 10;
        }
        if (model == null || model.isBlank()) {
            temp++;
        }

        CarPredicates.Builder carFilter = new CarPredicates.Builder();

        switch (temp) {
            case 1:     /* Model and Brand is not empty. */
            case 11: {  /* Model and Brand is empty. */
                throw new NotValidParamException("Only one of the parameters should be set.");
            }
            case 10: { /* Model is not empty. */
                carFilter = carFilter.brandEq(brand);
            }
            break;
            case 2: { /* Brand is not empty. */
                carFilter = carFilter.modelEq(model);
            }
        }

        return carList.stream().filter(carFilter.build())
                .mapToInt(CarFullInfo::getMaxSpeed)
                .average()
                .orElse(-1.0);
    }
}
