package com.anaximel.bmanager.appsecurity.repository;

import com.anaximel.bmanager.appsecurity.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {

    Car findCarById(Long carId);

    Car findCarByCarRegNumber(String carRegNumber);

    List<Car>  findCarByCarRegNumberContaining(String carRegNumber);




}
