package com.moneymie.carproject.controller;

import com.moneymie.carproject.model.Car;
import com.moneymie.carproject.payload.request.AddCarRequest;
import com.moneymie.carproject.payload.response.CarListResponse;
import com.moneymie.carproject.payload.response.CarResponse;
import com.moneymie.carproject.service.CarService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @ApiOperation(value = "This method is used to get sorted list of all cars in the database.")
    @GetMapping("/cars")
    @ResponseStatus(HttpStatus.OK)
    public CarListResponse findAll() {
        return carServices.findAll();
    }

    @ApiOperation(value = "This method is used to get the cars by vin.")
    @GetMapping("/car/{vin}")
    @ResponseStatus(HttpStatus.OK)
    public Car getCar(@PathVariable("vin")  String vin){
        return carServices.getCar(vin);
    }


    @ApiOperation(value = "This method is used to get matching records from any column in the database table.")
    @GetMapping("/cars/{searchParameter}")
    @ResponseStatus(HttpStatus.OK)
    public CarListResponse findByParameter(@PathVariable("searchParameter")  @ApiParam(name = "Search Parameter", value = "Field Value", example = "blue")String searchParameter){
        return carServices.findByParameter(searchParameter);
    }


    @ApiOperation(value = "This method is used fetch data from the database using Sort by and Sort order")
    @GetMapping("/cars/{searchParameter}/{orderBy}")
    @ResponseStatus(HttpStatus.OK)
    public CarListResponse findByParameterAndOrder(@PathVariable("searchParameter")  @ApiParam(name = "Search Parameter", value = "Sort By", example = "color")String searchParameter,
                                                   @PathVariable("orderBy")  @ApiParam(name = "OrderBy", value = "Sorting Order", example = "asc")String orderBy){
        return carServices.findByParameterAndOrder(searchParameter,orderBy);
    }


    @ApiOperation(value = "This method is used to add a new car detail to the database.")
    @PostMapping("/car")
    @ResponseStatus(HttpStatus.OK)
    public CarResponse addCar(@RequestBody AddCarRequest addCarRequest){
        return carServices.addCar(addCarRequest);
    }
}
