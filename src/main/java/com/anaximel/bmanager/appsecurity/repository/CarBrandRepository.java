package com.anaximel.bmanager.appsecurity.repository;

import com.anaximel.bmanager.appsecurity.domain.CarBrand;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarBrandRepository extends JpaRepository<CarBrand,Long> {

     CarBrand findCarBrandByCarBrandName(String carBrandName);

    @Override
    List<CarBrand> findAll();



}
