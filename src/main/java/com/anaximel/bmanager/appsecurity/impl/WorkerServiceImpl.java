package com.anaximel.bmanager.appsecurity.impl;

import com.anaximel.bmanager.appsecurity.domain.Worker;
import com.anaximel.bmanager.appsecurity.repository.WorkerRepositoryInterface;
import com.anaximel.bmanager.appsecurity.service.WorkerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WorkerServiceImpl implements WorkerServiceInterface {

    private WorkerRepositoryInterface workerRepositoryInterface;

    @Autowired
    WorkerServiceImpl (WorkerRepositoryInterface workerRepositoryInterface){
        this.workerRepositoryInterface = workerRepositoryInterface;
    }


    @Override
    public Worker getWorkerById(int id) {
        return this.workerRepositoryInterface.getOne(id);
    }

    @Override
    public Worker getWorkerByName(String name) {

        return this.workerRepositoryInterface.findWorkerByName(name);
    }

    @Override
    public List<Worker> getAllWorkers() {
        return this.workerRepositoryInterface.findAll();
    }
}
