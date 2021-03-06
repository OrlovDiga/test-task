package com.rincentral.test.util;

import com.rincentral.test.models.CarFullInfo;

import java.util.function.Predicate;

/**
 * @author Orlov Diga
 */
public final class CarPredicates {

    public static class Builder {

        private Predicate<CarFullInfo> multiPred = (info) -> true;

         public Builder countryEq(final String country) {
            if (country != null && !country.isBlank()) {
                Predicate<CarFullInfo> checkCountry = (info) -> info.getCountry().equals(country);
                this.multiPred = this.multiPred.and(checkCountry);
            }
            return this;
         }

        public Builder segmentEq(String segment) {
            if (segment != null && !segment.isBlank()) {
                Predicate<CarFullInfo> checkSegment = (info) -> info.getSegment().equals(segment);
                this.multiPred = this.multiPred.and(checkSegment);
            }
            return this;
         }

        public Builder hrEq(Integer minEngineHorsepower) {
            if (minEngineHorsepower != null && minEngineHorsepower > 0) {
                Predicate<CarFullInfo> checkHr =
                        (info) -> info.getEngineCar().getHorsepower() >= minEngineHorsepower;
                this.multiPred = this.multiPred.and(checkHr);
            }
            return this;
        }

        public Builder displacementEq(Double displacement) {
            if (displacement != null && displacement > 0.0) {
                Predicate<CarFullInfo> checkDisplacement =
                        (info) -> info.getEngineCar().getDisplacement() >= displacement;
                this.multiPred = this.multiPred.and(checkDisplacement);
            }
            return this;
        }

        public Builder speedMore(Integer minMaxSpeed) {
            if (minMaxSpeed != null && minMaxSpeed > 0) {
                Predicate<CarFullInfo> checkSpeed =
                        (info) -> info.getMaxSpeed() >= minMaxSpeed;
                this.multiPred = this.multiPred.and(checkSpeed);
            }
            return this;
        }

        public Builder bodyStyleEq(String bodyStyle) {
            if (bodyStyle != null && !bodyStyle.isBlank()) {
                Predicate<CarFullInfo> checkBodyStyle =
                        (info) -> info.getBodyCar().getStyle().contains(bodyStyle);
                this.multiPred = this.multiPred.and(checkBodyStyle);
            }
            return this;
        }

        public Builder containsYear(Integer year) {
            if (year != null && year > 0) {
                Predicate<CarFullInfo> checkYear =
                        (info) -> info.getStartYearProd() <= year && info.getEndYearProd() >= year;
                this.multiPred = this.multiPred.and(checkYear);
            }
            return this;
        }

        public Builder modelEq(String model) {
            if (model != null && !model.isBlank()) {
                Predicate<CarFullInfo> checkModel = (info) -> info.getModel().equals(model);
                this.multiPred = this.multiPred.and(checkModel);
            }
            return this;
        }

        public Builder brandEq(String brand) {
                if (brand != null && !brand.isBlank()) {
                    Predicate<CarFullInfo> checkBrand = (info) -> info.getBrand().equals(brand);
                    this.multiPred = this.multiPred.and(checkBrand);
                }
                return this;
        }

        public Predicate<CarFullInfo> build () {
             return multiPred;
        }
    }
}