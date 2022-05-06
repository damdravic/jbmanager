package com.anaximel.bmanager.appsecurity.impl;

import com.anaximel.bmanager.appsecurity.domain.WorkerQualification;
import com.anaximel.bmanager.appsecurity.repository.WorkerQualificationRepositoryInterface;
import com.anaximel.bmanager.appsecurity.service.WorkerQualificationServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WorkerQualificationServiceImpl implements WorkerQualificationServiceInterface {


    private WorkerQualificationRepositoryInterface workerQualificationRepositoryInterface;

    @Autowired
    WorkerQualificationServiceImpl (WorkerQualificationRepositoryInterface workerQualificationRepositoryInterface){
        this.workerQualificationRepositoryInterface = workerQualificationRepositoryInterface;
    }




    @Override
    public WorkerQualification getQualificationById(int id) {
        return this.workerQualificationRepositoryInterface.findWorkerQualificationById(id);
    }

    @Override
    public List<WorkerQualification> getAllQualifications() {
        return this.workerQualificationRepositoryInterface.findAll();
    }
}
