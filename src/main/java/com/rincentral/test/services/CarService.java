package com.rincentral.test.services;

import com.rincentral.test.models.CarInfo;

import java.util.List;

/**
 * @author Orlov Diga
 */
public interface CarService {

    List<? extends CarInfo> findCarsAllByParam(String country,
                                               String segment,
                                               Double minEngineDisplacement,
                                               Integer minEngineHorsepower,
                                               Integer minMaxSpeed,
                                               Integer search,
                                               Boolean isFull,
                                               Integer year,
                                               String bodyStyle);

    List<String> findAllBodyStyles();

    List<String> findAllEngineTypes();

    List<String> findAllWheelDrives();

    List<String> findAllFuelTypes();

    List<String> findAllGearboxTypes();

    double findAllAverageSpeed(String brand, String model);
}
