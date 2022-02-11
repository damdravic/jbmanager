package com.anaximel.bmanager.appsecurity.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="car_brand")

public class CarBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false,name="car_brand_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public int carBrandId;

    @Column(name="car_brand_name")
    public String carBrandName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "carBrandId")
    public List<CarModel> carModels;

   public CarBrand(){
   }

    public List<CarModel> getCarModels() {
        return carModels;
    }

    public void setCarModels(List<CarModel> carModels) {
        this.carModels = carModels;
    }

    public CarBrand(String carBrandName) {
        this.carBrandName = carBrandName;
    }

    public int getCarBrandId() {
        return carBrandId;
    }

    public void setCarBrandId(int carBrandId) {
        this.carBrandId = carBrandId;
    }

    public String getCarBrandName() {
        return carBrandName;
    }

    public void setCarBrandName(String carBrandName) {
        this.carBrandName = carBrandName;
    }

    @Override
    public String toString() {
        return "CarBrand{" +
                "carBrandId=" + carBrandId +
                ", carBrandName='" + carBrandName + '\'' +
                '}';
    }
}
