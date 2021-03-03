package com.rincentral.test.models.params;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import static com.rincentral.test.util.MessageErrorConstant.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarRequestParameters {
    @Size(max = 100, message = "country" + OVER_LENGTH)
    private String country;

    @Size(max = 100, message = "segment" + OVER_LENGTH)
    private String segment;

    @Min(value = 0, message = "minEngineDisplacement" + NEG_NUM)
    @Max(value = 1000000, message = "minEngineDisplacement" + OVER_NUM)
    private Double minEngineDisplacement;

    @Min(value = 1, message = "minEngineHorsepower" + NEG_NUM)
    @Max(value = Integer.MAX_VALUE, message = "minEngineHorsepower" + OVER_NUM)
    private Integer minEngineHorsepower;

    @Min(value = 1, message = "minMaxSpeed" + NEG_NUM)
    @Max(value = Integer.MAX_VALUE, message = "minMaxSpeed" + OVER_NUM)
    private Integer minMaxSpeed;

    @Min(value = 1, message = "search" + NEG_NUM)
    @Max(value = 300, message = "search" + OVER_NUM)
    private Integer search;

    private Boolean isFull;

    @Min(value = 1, message = "year" + NEG_NUM)
    @Max(value = Integer.MAX_VALUE, message = "year" + OVER_NUM)
    private Integer year;

    @Size(max = 100, message = "country" + OVER_LENGTH)
    private String bodyStyle;
}
