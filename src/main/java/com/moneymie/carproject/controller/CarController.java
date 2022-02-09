package com.moneymie.carproject.controller;

import com.moneymie.carproject.model.Car;
import com.moneymie.carproject.payload.request.AddCarRequest;
import com.moneymie.carproject.payload.response.CarListResponse;
import com.moneymie.carproject.payload.response.CarResponse;
import com.moneymie.carproject.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/api")
public class CarController {


    private final CarService carServices;

    @Autowired
    public CarController(CarService carServices) {
        this.carServices = carServices;
    }


    @GetMapping("/cars")
    @ResponseStatus(HttpStatus.OK)
    public CarListResponse findAll() {
        return carServices.findAll();
    }


    @GetMapping("/car/{vin}")
    @ResponseStatus(HttpStatus.OK)
    public Car getCar(@PathVariable("vin")  String vin){
        return carServices.getCar(vin);
    }



    @GetMapping("/cars/{searchParameter}")
    @ResponseStatus(HttpStatus.OK)
    public CarListResponse findByParameter(@PathVariable("searchParameter")  String searchParameter){
        return carServices.findByParameter(searchParameter);
    }



    @GetMapping("/cars/{searchParameter}/{orderBy}")
    @ResponseStatus(HttpStatus.OK)
    public CarListResponse findByParameterAndOrder(@PathVariable("searchParameter")  String searchParameter, @PathVariable("orderBy")  String orderBy){
        return carServices.findByParameterAndOrder(searchParameter,orderBy);
    }



    @PostMapping("/car")
    @ResponseStatus(HttpStatus.OK)
    public CarResponse addCar(@RequestBody AddCarRequest addCarRequest){
        return carServices.addCar(addCarRequest);
    }
}
