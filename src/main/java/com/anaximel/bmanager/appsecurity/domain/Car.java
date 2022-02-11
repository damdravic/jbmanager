package com.anaximel.bmanager.appsecurity.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="car_table")

public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
    @Column(nullable = false,unique = true,updatable = false)
    private String carRegNumber;
    private String carBrand;
    private String carModel;
    private String carOwner;
    private LocalDate carFirstReg;
    private String carColor;
    private boolean carSold;
    private boolean carIsActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarRegNumber() {
        return carRegNumber;
    }

    public void setCarRegNumber(String carRegNumber) {
        this.carRegNumber = carRegNumber;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(String carOwner) {
        this.carOwner = carOwner;
    }

    public LocalDate getCarFirstReg() {
        return carFirstReg;
    }

    public void setCarFirstReg(LocalDate carFirstReg) {
        this.carFirstReg = carFirstReg;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public boolean isCarSold() {
        return carSold;
    }

    public void setCarSold(boolean carSold) {
        this.carSold = carSold;
    }

    public boolean isCarIsActive() {
        return carIsActive;
    }

    public void setCarIsActive(boolean carIsActive) {
        this.carIsActive = carIsActive;
    }

    public Car() {
    }

    public Car( String carRegNumber, String carBrand, String carModel, String carOwner, LocalDate carFirstReg, String carColor, boolean carSold, boolean carIsActive) {
        this.carRegNumber = carRegNumber;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carOwner = carOwner;
        this.carFirstReg = carFirstReg;
        this.carColor = carColor;
        this.carSold = carSold;
        this.carIsActive = carIsActive;
    }

    //Constructor without RegistrationNumber for update car
    public Car(String carBrand, String carModel, String carOwner, LocalDate carFirstReg, String carColor, boolean carSold, boolean carIsActive) {
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carOwner = carOwner;
        this.carFirstReg = carFirstReg;
        this.carColor = carColor;
        this.carSold = carSold;
        this.carIsActive = carIsActive;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carRegNumber='" + carRegNumber + '\'' +
                ", carBrand='" + carBrand + '\'' +
                ", carModel='" + carModel + '\'' +
                ", carOwner='" + carOwner + '\'' +
                ", carFirstReg='" + carFirstReg + '\'' +
                ", carColor='" + carColor + '\'' +
                ", carSold=" + carSold +
                ", carIsActive=" + carIsActive +
                '}';
    }
}
