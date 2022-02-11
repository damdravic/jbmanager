package com.anaximel.bmanager.appsecurity.impl;

import com.anaximel.bmanager.appsecurity.domain.CarBrand;
import com.anaximel.bmanager.appsecurity.domain.CarModel;
import com.anaximel.bmanager.appsecurity.repository.CarBrandRepository;
import com.anaximel.bmanager.appsecurity.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CarBrandServiceImpl implements CarBrandService {

    private CarBrandRepository      carBrandRepository;


    @Autowired
    public CarBrandServiceImpl(CarBrandRepository carBrandRepository){
        this.carBrandRepository = carBrandRepository;
    }



    @Override
    public CarBrand getCarBrand(String carBrandName) {
      CarBrand carBrand = this.carBrandRepository.findCarBrandByCarBrandName(carBrandName);
      return carBrand;
    }

    @Override
    public List<CarBrand> getAllCarBrands() {
        List<CarBrand>  carBrands = this.carBrandRepository.findAll();
        return carBrands;
    }

    @Override
    public CarBrand addNewCarBrand(String carBrandName) {
         CarBrand newCarBrand = new CarBrand(carBrandName);
         carBrandRepository.save(newCarBrand);
         return newCarBrand;

    }

    @Override
    public CarModel updateBrand(String brandName,String modelName){
        CarBrand carBrand = this.carBrandRepository.findCarBrandByCarBrandName(brandName);
        CarModel carModel = new CarModel(modelName);
           carModel.setActive(true);
        carBrand.getCarModels().add(carModel);
          this.carBrandRepository.save(carBrand);
          return carModel;



    }

    @Override
    public void deleteCarBrand(String carBrandName) {
         CarBrand deleteCarBrand = this.carBrandRepository.findCarBrandByCarBrandName(carBrandName);
         carBrandRepository.delete(deleteCarBrand);

    }


}
