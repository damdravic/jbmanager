package com.anaximel.bmanager.appsecurity.service;

import com.anaximel.bmanager.appsecurity.domain.CarBrand;
import com.anaximel.bmanager.appsecurity.domain.CarModel;

import java.util.List;

public interface CarBrandService {

    CarBrand getCarBrand(String carBrandName);

    List<CarBrand> getAllCarBrands();

    CarModel updateBrand(String selectedBrand, String newModel);

    CarBrand addNewCarBrand(String carBrandName);

    void deleteCarBrand(String carBrandName);

}
