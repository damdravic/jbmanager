package com.anaximel.bmanager.appsecurity.service;

import com.anaximel.bmanager.appsecurity.domain.Worker;

import java.util.List;

public interface WorkerServiceInterface {

    Worker addNewWorker(Worker worker);

    Worker updateWorker(int id, Worker Worker);

    Worker getWorkerById(int id);

    Worker getWorkerByName (String name);

    List<Worker> getAllWorkers();

}
