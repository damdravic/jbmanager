package com.anaximel.bmanager.appsecurity.repository;

import com.anaximel.bmanager.appsecurity.domain.WorkerQualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface WorkerQualificationRepositoryInterface extends JpaRepository<WorkerQualification,Integer> {

    WorkerQualification findWorkerQualificationById(int id);

    WorkerQualification findWorkerQualificationByName(String name);


    @NonNull
    List<WorkerQualification> findAll();





}
