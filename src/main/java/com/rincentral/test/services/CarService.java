package com.rincentral.test.services;

import com.rincentral.test.models.CarInfo;
import com.rincentral.test.models.params.CarRequestParameters;

import java.util.List;

/**
 * @author Orlov Diga
 */
public interface CarService {

    List<? extends CarInfo> findCarsAllByParam(CarRequestParameters params);

    List<String> findAllBodyStyles();

    List<String> findAllEngineTypes();

    List<String> findAllWheelDrives();

    List<String> findAllFuelTypes();

    List<String> findAllGearboxTypes();

    double findAllAverageSpeed(String brand, String model);
}
