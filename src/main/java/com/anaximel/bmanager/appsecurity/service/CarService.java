package com.anaximel.bmanager.appsecurity.service;

import com.anaximel.bmanager.appsecurity.domain.Car;
import com.anaximel.bmanager.appsecurity.exception.domain.RegistrationNumberException;
import com.anaximel.bmanager.appsecurity.repository.CarRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface CarService {

    List<Car> getCars();

    Car getCarByRegNumber(String regNumber);

    Car addNewCar(String carRegNumber,String carBrand,
                  String carModel, String carOwner,
                  LocalDate carFirstReg, String carColor,
                  boolean carSold, boolean carIsActive)
                    throws RegistrationNumberException, IOException;

    Car updateCar(String currentCarRegNr, String newCarRegNumber, String newCarBrand,
                  String newCarModel, String newCarOwner,
                  LocalDate newCarFirstReg, String newCarColor,
                  boolean newCarSold, boolean newCarIsActive);

    List<Car> getCar(String licencePlate);


    void deleteCar(String carRegNumber);

}
