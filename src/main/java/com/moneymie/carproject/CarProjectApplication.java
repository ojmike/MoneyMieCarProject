package com.moneymie.carproject;

import com.moneymie.carproject.controller.StartUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CarProjectApplication.class, args);
    }

    private StartUpService startUpService;
    @Autowired

    public CarProjectApplication(StartUpService startUpService) {
        this.startUpService = startUpService;
    }

    @Override
    public void run(String... args) throws Exception {
        startUpService.getCarList();
    }

}
