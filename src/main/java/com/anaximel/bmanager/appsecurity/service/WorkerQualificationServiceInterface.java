package com.anaximel.bmanager.appsecurity.service;

import com.anaximel.bmanager.appsecurity.domain.WorkerQualification;

import java.util.List;

public interface WorkerQualificationServiceInterface {

    WorkerQualification getQualificationById(int id);

    List<WorkerQualification> getAllQualifications();



}
