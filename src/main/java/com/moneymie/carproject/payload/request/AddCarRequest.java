package com.moneymie.carproject.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.moneymie.carproject.model.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddCarRequest {
    @JsonProperty("vin")
    private String vin;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("year")
    private Integer year;
    @JsonProperty("color")
    private String color;
}
