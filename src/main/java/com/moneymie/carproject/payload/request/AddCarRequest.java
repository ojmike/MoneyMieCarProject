package com.moneymie.carproject.payload.request;

import com.moneymie.carproject.model.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCarRequest {
    private String vin;
    private String brand;
    private Integer year;
    private String color;
}
