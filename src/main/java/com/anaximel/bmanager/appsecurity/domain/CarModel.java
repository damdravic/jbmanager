package com.anaximel.bmanager.appsecurity.domain;

import javax.persistence.*;

@Entity
@Table(name="car_model")
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false,nullable = false,name="id_model")
    private int idModel;


    @Column(name="model_name")
    private String modelName;


    private boolean isActive;


    public CarModel(){
    }

    public CarModel( String modelName) {
        this.modelName = modelName;
    }

    public int getIdModel() {
        return idModel;
    }

    public void setIdModel(int idModel) {
        this.idModel = idModel;
    }






    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "idModel=" + idModel +
                ", modelName='" + modelName + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
