package com.rincentral.test.models.params;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.rincentral.test.util.MessageErrorConstant.OVER_LENGTH;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaxSpeedRequestParameters {
    @Size(max = 100, message = "brand" + OVER_LENGTH)
    private String brand;
    @Size(max = 100, message = "model" + OVER_LENGTH)
    private String model;
}
