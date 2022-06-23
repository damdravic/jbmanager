package com.anaximel.bmanager.appsecurity.service;

import com.anaximel.bmanager.appsecurity.domain.WorkerQualification;

import java.util.List;

public interface WorkerQualificationServiceInterface {

    WorkerQualification addNewWorkerQualification(String name,String description);

    WorkerQualification getQualificationById(int id);

    List<WorkerQualification> getAllQualifications();



}
