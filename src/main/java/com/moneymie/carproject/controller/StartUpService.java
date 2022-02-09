package com.moneymie.carproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moneymie.carproject.model.Car;
import com.moneymie.carproject.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Component
public class StartUpService {

    private List<Car> carList;

    private final WebClient webClient;

    private CarRepository carRepository;



    @Autowired
    public StartUpService(WebClient webClient, CarRepository carRepository) {
        this.webClient = webClient;
        this.carRepository = carRepository;
    }

    public List<Car> getCarList() throws JsonProcessingException {

        var jsonString= webClient.get()
                .uri("/cars-large.json")
                .retrieve()
                .bodyToMono(String.class)
                .block();


        ObjectMapper objectMapper = new ObjectMapper();
        Car[] pp1 = objectMapper.readValue(jsonString, Car[].class);

        List<Car> items = Arrays.asList(pp1);

        items.forEach(car -> carRepository.save(car));

        return items;

    }



}
