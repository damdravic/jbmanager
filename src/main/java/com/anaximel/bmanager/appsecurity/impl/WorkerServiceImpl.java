package com.anaximel.bmanager.appsecurity.impl;

import com.anaximel.bmanager.appsecurity.domain.Worker;
import com.anaximel.bmanager.appsecurity.repository.WorkerRepositoryInterface;
import com.anaximel.bmanager.appsecurity.service.WorkerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class WorkerServiceImpl implements WorkerServiceInterface {

    private WorkerRepositoryInterface workerRepositoryInterface;

    @Autowired
    WorkerServiceImpl (WorkerRepositoryInterface workerRepositoryInterface){
        this.workerRepositoryInterface = workerRepositoryInterface;
    }

    public WorkerServiceImpl() {
    }

    @Override
    public Worker addNewWorker(Worker worker) {
        return null;
    }

    @Override
    public Worker updateWorker(int id, Worker Worker) {
        return null;
    }

    @Override
    public Worker getWorkerById(int id) {
        return this.workerRepositoryInterface.getOne(id);
    }

    @Override
    public Worker getWorkerByName(String username) {

        return this.workerRepositoryInterface.findWorkerByUsername(username);
    }

    @Override
    public List<Worker> getAllWorkers() {
        return this.workerRepositoryInterface.findAll();
    }
}
