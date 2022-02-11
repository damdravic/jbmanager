package com.anaximel.bmanager.appsecurity.impl;

import com.anaximel.bmanager.appsecurity.domain.Car;
import com.anaximel.bmanager.appsecurity.exception.domain.RegistrationNumberException;
import com.anaximel.bmanager.appsecurity.repository.CarRepository;
import com.anaximel.bmanager.appsecurity.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;


    @Autowired
    public CarServiceImpl(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    @Override
    public Car getCarByRegNumber(String regNumber){
        return carRepository.findCarByCarRegNumber(regNumber);
    }


    public List<Car> getAllCars(){
        return carRepository.findAll();
    }


    @Override
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @Override
    public Car addNewCar(String carRegNumber, String carBrand, String carModel, String carOwner, LocalDate carFirstReg, String carColor, boolean carSold, boolean carIsActive) throws RegistrationNumberException, IOException {
        Car newCar =  new Car(carRegNumber, carBrand, carModel,
        carOwner, carFirstReg, carColor, carSold, carIsActive);

        carRepository.save(newCar);
        return newCar;
    }

    @Override
    public Car updateCar(String currentCarRegNr,String newCarRegNumber, String newCarBrand, String newCarModel, String newCarOwner, LocalDate newCarFirstReg, String newCarColor, boolean newCarSold, boolean newCarIsActive) {

        Car updatedCar = getCarByRegNumber(currentCarRegNr);
        updatedCar.setCarRegNumber(newCarRegNumber);
        updatedCar.setCarBrand(newCarBrand);
        updatedCar.setCarModel(newCarModel);
        updatedCar.setCarOwner(newCarOwner);
        updatedCar.setCarFirstReg(newCarFirstReg);
        updatedCar.setCarColor(newCarColor);
        updatedCar.setCarSold(newCarSold);
        updatedCar.setCarIsActive(newCarIsActive);


        carRepository.save(updatedCar) ;
        return updatedCar;
    }

    @Override
    public void deleteCar(String carRegNumber) {
        Car car= carRepository.findCarByCarRegNumber(carRegNumber);
        carRepository.deleteById(car.getId());

    }
}
