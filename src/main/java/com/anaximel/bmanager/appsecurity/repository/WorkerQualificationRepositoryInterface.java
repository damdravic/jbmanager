package com.anaximel.bmanager.appsecurity.repository;

import com.anaximel.bmanager.appsecurity.domain.WorkerQualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface WorkerQualificationRepositoryInterface extends JpaRepository<WorkerQualification,Integer> {


    WorkerQualification findWorkerQualificationByWorkerQualificationId(int workerQualificationId);

    WorkerQualification findWorkerQualificationByWorkerQualificationName(String workerQualificationName);


    @Override
    List<WorkerQualification> findAll();





}
