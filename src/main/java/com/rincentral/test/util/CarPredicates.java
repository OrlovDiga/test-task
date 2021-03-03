package com.rincentral.test.util;

import com.rincentral.test.models.CarFullInfo;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * @author Orlov Diga
 */
public class CarPredicates {


    private CarPredicates() {

    }

//    private static Predicate<CarFullInfo> multiPred = (info) -> true;

  /*  public Predicate<CarFullInfo> getFilter() {

    }*/

    public static class Builder {

       /* private static BiPredicate<CarFullInfo, String> checkCountry =
                (info, country) -> info.getCountry().equals(country);
        private static BiPredicate<CarFullInfo, String> checkSegment =
                (info, segment) -> info.getSegment().equals(segment);
        private static BiPredicate<CarFullInfo, Double> checkDisplacement =
                (info, minEngineDisplacement) -> info.getEngineCar().getEngineDisplacement() >= minEngineDisplacement;
        private static BiPredicate<CarFullInfo, Integer> checkHr =
                (info, minEngineHorsepower) -> info.getEngineCar().getEngineHorsepower() >= minEngineHorsepower;
        private static BiPredicate<CarFullInfo, Integer> checkSpeed=
                (info, minMaxSpeed) -> info.getMaxSpeed() >= minMaxSpeed;
        private static BiPredicate<CarFullInfo, String> checkBodyStyle =
                (info, bodyStyle) -> info.getBodyCar().getBodyStyle().equals(bodyStyle);
        private static BiPredicate<CarFullInfo, Integer> checkYear =
                (info, year) -> info.getStartYearProd() <= year && info.getEndYearProd() >= year;*/
        private Predicate<CarFullInfo> multiPred = (info) -> true;

         Builder countryEq(String country) {
            Predicate<CarFullInfo> checkCountry = (info) -> info.getCountry().equals(country);
            this.multiPred = this.multiPred.and(checkCountry);
            return this;
         }

         Builder segmentEq(String segment) {
            Predicate<CarFullInfo> checkSegment = (info) -> info.getSegment().equals(segment);
            this.multiPred = this.multiPred.and(checkSegment);
            return this;
         }

        Builder hrEq(Integer minEngineHorsepower) {
            Predicate<CarFullInfo> checkHr = (info) -> info.getEngineCar().getEngineHorsepower() >= minEngineHorsepower;
            this.multiPred = this.multiPred.and(checkHr);
            return this;
        }

        Builder displacementEq(Double displacement) {
            Predicate<CarFullInfo> checkDisplacement = (info) -> info.getEngineCar().getEngineDisplacement() >= displacement;
            this.multiPred = this.multiPred.and(checkDisplacement);
            return this;
        }

        Builder speedMore(Integer minMaxSpeed) {
            Predicate<CarFullInfo> checkSpeed= (info) -> info.getMaxSpeed() >= minMaxSpeed;
            this.multiPred = this.multiPred.and(checkSpeed);
            return this;
        }

        Builder bodyStyleEq(String bodyStyle) {
            Predicate<CarFullInfo> checkBodyStyle = (info) -> info.getBodyCar().getBodyStyle().equals(bodyStyle);
            this.multiPred = this.multiPred.and(checkBodyStyle);
            return this;
        }

        Builder containsYear(Integer year) {
            Predicate<CarFullInfo> checkYear = (info) -> info.getStartYearProd() <= year && info.getEndYearProd() >= year;

            return this;
        }

    }
}

/*
*       Predicate<CarFullInfo> checkCountry = (info) -> info.getCountry().equals(country);
        Predicate<CarFullInfo> checkSegment = (info) -> info.getSegment().equals(segment);
        Predicate<CarFullInfo> checkDisplacement = (info) -> info.getEngineCar().getEngineDisplacement() >= minEngineDisplacement;
        Predicate<CarFullInfo> checkHr = (info) -> info.getEngineCar().getEngineHorsepower() >= minEngineHorsepower;
        Predicate<CarFullInfo> checkSpeed= (info) -> info.getMaxSpeed() >= minMaxSpeed;
        Predicate<CarFullInfo> checkBodyStyle = (info) -> info.getBodyCar().getBodyStyle().equals(bodyStyle);
        Predicate<CarFullInfo> checkYear = (info) -> info.getStartYearProd() <= year && info.getEndYearProd() >= year;
* */
