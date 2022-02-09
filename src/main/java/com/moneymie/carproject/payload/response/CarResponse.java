package com.moneymie.carproject.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {

    private Long id;

    private String vin;

    private String brand;

    private int year;

    private String color;
}
