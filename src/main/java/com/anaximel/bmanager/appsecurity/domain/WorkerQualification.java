package com.anaximel.bmanager.appsecurity.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class WorkerQualification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private int workerQualificationId;

    private String workerQualificationName;
    private String workerQualificationDescription;

    public WorkerQualification(int workerQualificationId, String workerQualificationName, String workerQualificationDescription) {
        this.workerQualificationId = workerQualificationId;
        this.workerQualificationName = workerQualificationName;
        this.workerQualificationDescription = workerQualificationDescription;
    }

    public int getWorkerQualificationId() {
        return workerQualificationId;
    }

    public void setWorkerQualificationId(int workerQualificationId) {
        this.workerQualificationId = workerQualificationId;
    }

    public String getWorkerQualificationName() {
        return workerQualificationName;
    }

    public void setWorkerQualificationName(String workerQualificationName) {
        this.workerQualificationName = workerQualificationName;
    }

    public String getWorkerQualificationDescription() {
        return workerQualificationDescription;
    }

    public void setWorkerQualificationDescription(String workerQualificationDescription) {
        this.workerQualificationDescription = workerQualificationDescription;
    }
}
