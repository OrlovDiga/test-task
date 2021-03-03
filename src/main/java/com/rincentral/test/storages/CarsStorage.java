package com.rincentral.test.storages;

import com.rincentral.test.models.BodyCharacteristics;
import com.rincentral.test.models.CarFullInfo;
import com.rincentral.test.models.EngineCharacteristics;
import com.rincentral.test.models.external.ExternalBrand;
import com.rincentral.test.models.external.ExternalCar;
import com.rincentral.test.models.external.ExternalCarInfo;
import com.rincentral.test.services.impl.ExternalCarsApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Orlov Diga
 */
@Component
@RequiredArgsConstructor
public class CarsStorage {

    private final ExternalCarsApiService apiService;
    private final List<CarFullInfo> carFullInfoList;

    @PostConstruct
    public void init() {
        List<ExternalCar> carList = apiService.loadAllCars();
        Map<Integer, ExternalBrand> externalBrandMap =
                apiService.loadAllBrands().stream()
                        .collect(Collectors.toMap(ExternalBrand::getId, it -> it));
        for (ExternalCar car: carList) {
            ExternalCarInfo carInfo = apiService.loadCarInformationById(car.getId());

            ExternalBrand tempBrand = externalBrandMap.get(carInfo.getBrandId());
            String brand = tempBrand.getTitle();
            String country = tempBrand.getCountry();

            String[] extYearRange = carInfo.getYearsRange().split("-");
            int start = Integer.parseInt(extYearRange[0]);
            int end = (extYearRange[1].equals("present")) ? Integer.MAX_VALUE : Integer.parseInt(extYearRange[1]);

            BodyCharacteristics body = BodyCharacteristics.builder()
                    .bodyHeight(carInfo.getBodyHeight().toString())
                    .bodyLength(carInfo.getBodyLength().toString())
                    .bodyWidth(carInfo.getBodyWidth().toString())
                    .bodyStyle(carInfo.getBodyStyle())
                    .build();

            EngineCharacteristics engine = EngineCharacteristics.builder()
                    .engineDisplacement(carInfo.getEngineDisplacement().doubleValue())
                    .engineType(carInfo.getEngineType())
                    .engineHorsepower(carInfo.getHp())
                    .fuelType(carInfo.getFuelType().toString().toLowerCase())
                    .build();

            CarFullInfo carFullInfo = new CarFullInfo(carInfo.getId(),
                    carInfo.getSegment(),
                    brand,
                    carInfo.getModel(),
                    country,
                    carInfo.getGeneration(),
                    carInfo.getModification(),
                    start,
                    end,
                    carInfo.getGearboxType(),
                    carInfo.getWheelDriveType(),
                    body,
                    engine,
                    carInfo.getAcceleration().toString(),
                    carInfo.getMaxSpeed());

            carFullInfoList.add(carFullInfo);
        }
    }
}
