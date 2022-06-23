package com.anaximel.bmanager.appsecurity.impl;

import com.anaximel.bmanager.appsecurity.domain.WorkerQualification;
import com.anaximel.bmanager.appsecurity.repository.WorkerQualificationRepositoryInterface;
import com.anaximel.bmanager.appsecurity.service.WorkerQualificationServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class WorkerQualificationServiceImpl implements WorkerQualificationServiceInterface {


    private WorkerQualificationRepositoryInterface workerQualificationRepositoryInterface;

    @Autowired
    WorkerQualificationServiceImpl (WorkerQualificationRepositoryInterface workerQualificationRepositoryInterface){
        this.workerQualificationRepositoryInterface = workerQualificationRepositoryInterface;
    }


    @Override
    public WorkerQualification addNewWorkerQualification(String name, String description) {

         WorkerQualification wq = new WorkerQualification(name,description);
         this.workerQualificationRepositoryInterface.save(wq);
         return wq;
    }

    @Override
    public WorkerQualification getQualificationById(int id) {
        return this.workerQualificationRepositoryInterface.findWorkerQualificationByWorkerQualificationId(id);
    }

    @Override
    public List<WorkerQualification> getAllQualifications() {
        return this.workerQualificationRepositoryInterface.findAll();
    }
}
