package com.moneymie.carproject.service.implementation;

import com.moneymie.carproject.config.StartUpService;
import com.moneymie.carproject.exception.ApiBadRequestException;
import com.moneymie.carproject.exception.ApiResourceNotFoundException;
import com.moneymie.carproject.model.Car;
import com.moneymie.carproject.payload.request.AddCarRequest;
import com.moneymie.carproject.payload.response.CarListResponse;
import com.moneymie.carproject.payload.response.CarResponse;
import com.moneymie.carproject.repository.CarRepository;
import com.moneymie.carproject.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.persistence.criteria.Predicate;
import java.util.*;


@Service
public class CarServiceImpl implements CarService {



    private final WebClient webClient;

    private final StartUpService dataSource;

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(WebClient webClient, StartUpService dataSource,CarRepository carRepository) {
        this.webClient = webClient;
        this.dataSource  = dataSource;
        this.carRepository=carRepository;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceImpl.class);


    public CarListResponse findAll() {

        List<Car> cars = carRepository.findAll();
        Comparator<Car> byType = Comparator.comparing(Car::getBrand);
        Collections.sort(cars, byType);
        CarListResponse carListResponse = getCarListResponse(cars);
        return carListResponse;
    }

    public Car getCar(String vin)  {
        return   carRepository.findByVin(vin).orElseThrow(() -> new ApiResourceNotFoundException("Invalid VIN supplied"));
    }

    public CarListResponse findByParameter(String searchParameter) {

        List<Car> cars = carRepository.findByParameter(searchParameter.toLowerCase());
//        Comparator<Car> byType = Comparator.comparing(Car::getBrand);
//        Collections.sort(items, byType);
        CarListResponse carListResponse = getCarListResponse(cars);
        return  carListResponse;
    }

    public CarListResponse findByParameterAndOrder(String searchParameter, String orderBy) {

        try{
            List<Car> cars = carRepository.findAll((Specification<Car>) (root, criteriaQuery, criteriaBuilder) ->{
                Predicate predicate = criteriaBuilder.conjunction();
                criteriaQuery.orderBy(orderBy.equals("asc") ? criteriaBuilder.asc(root.get(searchParameter)): criteriaBuilder.desc(root.get(searchParameter)));
                return predicate;
            });

            CarListResponse carListResponse = getCarListResponse(cars);
            return carListResponse;

        }catch (IllegalArgumentException err){
            throw new ApiBadRequestException("Wrong Sort Parameter Specified");
        }catch (Exception err){
            throw new ApiBadRequestException("Wrong Sort Parameter Specified");
        }
    }

    private CarListResponse getCarListResponse(List<Car> cars) {
        List<CarResponse> carResponses = new ArrayList<>();
        for (Car car: cars) {
            carResponses.add(getCarResponse(car));
        }
        CarListResponse carListResponse = new CarListResponse();
        carListResponse.setCarResponseList(carResponses);
        return carListResponse;
    }

    public CarResponse addCar(AddCarRequest addCarRequest) {

        Optional<Car> carOptional = carRepository.findByVin(addCarRequest.getVin());
        if(carOptional.isPresent()){
         throw new ApiBadRequestException("VIN already registered in the system");
        }

        Car car = new Car();
        car.setVin(addCarRequest.getVin());
        car.setBrand(addCarRequest.getBrand());
        car.setColor(addCarRequest.getColor());
        car.setYear(addCarRequest.getYear());
        car = carRepository.save(car);
        CarResponse carResponse = getCarResponse(car);

        return carResponse;
    }

    private CarResponse getCarResponse(Car car) {
        CarResponse carResponse = new CarResponse();
        carResponse.setBrand(car.getBrand());
        carResponse.setVin(car.getVin());
        carResponse.setColor(car.getColor());
        carResponse.setYear(car.getYear());
        carResponse.setId(car.getId());
        return carResponse;
    }


}
