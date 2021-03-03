package com.rincentral.test.services.impl;

import com.rincentral.test.models.CarFullInfo;
import com.rincentral.test.models.CarInfo;
import com.rincentral.test.services.CarService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Orlov Diga
 */
@Service("CarService")
public class CarServiceImpl implements CarService {

    private List<CarFullInfo> carList;

    @Override
    public List<? extends CarInfo> findCarsAllByParam(final String country,
                                                      final String segment,
                                                      final Double minEngineDisplacement,
                                                      final Integer minEngineHorsepower,
                                                      final Integer minMaxSpeed,
                                                      final Integer search,
                                                      final Boolean isFull,
                                                      final Integer year,
                                                      final String bodyStyle) {

        Predicate<CarFullInfo> checkCountry = (info) -> info.getCountry().equals(country);
        Predicate<CarFullInfo> checkSegment = (info) -> info.getSegment().equals(segment);
        Predicate<CarFullInfo> checkDisplacement = (info) -> info.getEngineCar().getEngineDisplacement() >= minEngineDisplacement;
        Predicate<CarFullInfo> checkHr = (info) -> info.getEngineCar().getEngineHorsepower() >= minEngineHorsepower;
        Predicate<CarFullInfo> checkSpeed= (info) -> info.getMaxSpeed() >= minMaxSpeed;
        Predicate<CarFullInfo> checkBodyStyle = (info) -> info.getBodyCar().getBodyStyle().equals(bodyStyle);
        Predicate<CarFullInfo> checkYear = (info) -> info.getStartYearProd() <= year && info.getEndYearProd() >= year;

        Predicate<CarFullInfo> multiFilter = (info) -> true;

        if (!country.isBlank()) {
            multiFilter = multiFilter.and(checkCountry);
        }
        if (!segment.isBlank()) {
            multiFilter = multiFilter.and(checkSegment);
        }
        if (minEngineDisplacement != -1.0) {
            multiFilter = multiFilter.and(checkDisplacement);
        }
        if (minEngineHorsepower != -1) {
            multiFilter = multiFilter.and(checkHr);
        }
        if (minMaxSpeed != -1) {
            multiFilter = multiFilter.and(checkSpeed);
        }
        if (year != -1) {
            multiFilter = multiFilter.and(checkYear);
        }
        if (!bodyStyle.isBlank()) {
            multiFilter = multiFilter.and(checkBodyStyle);
        }

        Stream<CarFullInfo> streamCars = carList.stream().filter(multiFilter);

      /*  final boolean isCountry = !country.isBlank();
        final boolean isSegment = !segment.isBlank();
        final boolean isMinEngineDisplacement = minEngineDisplacement != -1.0;
        final boolean isMinEngineHorsepower = minEngineHorsepower != -1;
        final boolean isMinMaxSpeed = minMaxSpeed != -1;
        final boolean isSearch = search != 0;
        final boolean isYear = year != -1;
        final boolean isBodyStyle = !bodyStyle.isBlank();

        Stream<CarFullInfo> streamCars = carList.stream()
                .filter(it -> {
                    int temp = 1;
                    if (isCountry) {
                        temp *= it.getCountry().equals(country) ? 1 : 0;
                    }
                    if (isSegment) {
                        temp *= it.getSegment().equals(segment) ? 1 : 0;
                    }
                    if (isMinEngineDisplacement) {
                        temp *= it.getEngineCar().getEngineDisplacement() >= minEngineDisplacement ? 1 : 0;
                    }
                    if (isMinEngineHorsepower) {
                        temp *= it.getEngineCar().getEngineHorsepower() >= minEngineHorsepower ? 1 : 0;
                    }
                    if (isMinMaxSpeed) {
                        temp *= it.getMaxSpeed() >= minMaxSpeed ? 1 : 0;
                    }
                    if (isBodyStyle) {
                        temp *= it.getBodyCar().getBodyStyle().equals(bodyStyle) ? 1 : 0;
                    }
                    if (isYear) {
                        temp *= it.getStartYearProd() <= year && it.getEndYearProd() >= year ? 1 : 0;
                    }
                    return temp == 1;
                });*/

        if (search > 0) {
            streamCars = streamCars.sorted(Comparator
                                    .comparing(CarFullInfo::getModel)
                                    .thenComparing(CarFullInfo::getGeneration)
                                    .thenComparing(CarFullInfo::getModification))
            .limit(search);
        }

        return isFull ?
                streamCars.collect(Collectors.toList()) :
                streamCars.map(it -> (CarInfo) it).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBodyStyles() {
        return carList.stream()
                .map(it -> it.getBodyCar().getBodyStyle())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllEngineTypes() {
        return carList.stream()
                .map(it -> it.getEngineCar().getEngineType())
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
        if (!brand.isBlank() && !model.isBlank() || brand.isBlank() && model.isBlank()) {
            throw new UnsupportedOperationException("");
        }

        Stream<CarFullInfo> carFullInfoStream = carList.stream();
        if (brand.isBlank()) {
            carFullInfoStream = carFullInfoStream
                    .filter(it -> it.getModel().equals(model));
        }
        if (model.isBlank()) {
            carFullInfoStream = carFullInfoStream
                    .filter(it -> it.getBrand().equals(brand));
        }

        return carFullInfoStream
                .mapToInt(CarFullInfo::getMaxSpeed)
                .average()
                .orElse(-1.0);
    }

/*    public static void main(String[] args) {
        CarServiceImpl service = new CarServiceImpl();
        CarFullInfo car1 = new CarFullInfo(1, "segemnt", "brand", "model", "country", "generation", "modification", 1, 1, "gearbox", "wheelDrive", new BodyCharacteristics(), new EngineCharacteristics(), "acceleration", 1);
        CarFullInfo car2 = new CarFullInfo(2, "segemnt", "brand", "model", "country1", "generation", "modification", 1, 1, "gearbox", "wheelDrive", new BodyCharacteristics(), new EngineCharacteristics(), "acceleration", 1);
        CarFullInfo car3 = new CarFullInfo(3, "segemnt", "brand", "model2", "country1", "generation", "modification", 1, 1, "gearbox", "wheelDrive", new BodyCharacteristics(), new EngineCharacteristics(), "acceleration", 1);
        CarFullInfo car4 = new CarFullInfo(4, "segemnt", "brand1", "model2", "country1", "generation", "modification", 1, 1, "gearbox", "wheelDrive", new BodyCharacteristics(), new EngineCharacteristics(), "acceleration", 1);
        CarFullInfo car5 = new CarFullInfo(5, "segemnt", "brand1", "model2", "country", "generation", "modification", 1, 1, "gearbox", "wheelDrive", new BodyCharacteristics(), new EngineCharacteristics(), "acceleration", 1);
        CarFullInfo car6 = new CarFullInfo(6, "segemnt", "brand1", "model2", "country", "generation", "modification", 1, 1, "gearbox", "wheelDrive", new BodyCharacteristics(), new EngineCharacteristics(), "acceleration", 1);
        List<CarFullInfo> cars = Arrays.asList(car1, car2, car3, car4 ,car5, car6);
        service.carList = cars;
        CarRequestParameters parameters = new CarRequestParameters();
        parameters.setCountry("country1");
        parameters.setSegment("segemnt");
        parameters.setMinEngineDisplacement(-1.0);
        parameters.setMinEngineHorsepower(-1);
        parameters.setMinMaxSpeed(1);

        ObjectMapper mapper = new ObjectMapper();
        parameters.setIsFull(true);
        parameters.setYear(1);
        parameters.setBodyStyle("");
        List<String> jsonList= new ArrayList<>();
        service.findFullCarsAllByParam(parameters).forEach(it -> {
                ObjectWriter writer = mapper.writerFor(it.getTemp());
            try {
                jsonList.add(writer.writeValueAsString(it));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        jsonList.forEach(System.out::println);
    }*/
}
