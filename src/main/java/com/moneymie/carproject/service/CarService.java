package com.moneymie.carproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.moneymie.carproject.model.Car;
import com.moneymie.carproject.payload.request.AddCarRequest;
import com.moneymie.carproject.payload.response.CarResponse;

import java.io.IOException;
import java.util.List;


public interface CarService {
    List<Car> findAll() ;

    Car getCar(String vin) ;

    List<Car> findByParameter(String searchParemeter);

    List<CarResponse> findByParameterAndOrder(String searchParemeter, String orderBy);

    CarResponse addCar(AddCarRequest addCarRequest);

}
