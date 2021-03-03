package com.rincentral.test.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class BodyCharacteristics {

    @JsonProperty("length")
    private String length;

    @JsonProperty("width")
    private String width;

    @JsonProperty("height")
    private String height;

    @JsonProperty("style")
    private String style;
}
