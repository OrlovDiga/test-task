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

    @JsonProperty("body_length")
    private String bodyLength;

    @JsonProperty("body_width")
    private String bodyWidth;

    @JsonProperty("body_height")
    private String bodyHeight;

    @JsonProperty("body_style")
    private String bodyStyle;
}
