package com.anaximel.bmanager.appsecurity.service;

import com.anaximel.bmanager.appsecurity.domain.Worker;
import com.anaximel.bmanager.appsecurity.domain.WorkerQualification;
import com.anaximel.bmanager.appsecurity.domain.Workshop;

import java.util.List;

public interface WorkshopServiceInterface {

    Workshop getWorkshopId(int id);

    List<Workshop> getAllWorkshops();


    Workshop addNewWorkshop(String wsName,
                            String wsImage,
                            boolean wsIsActive,
                            String wsDescription,
                            List<Worker> wsWorkers,
                            List<WorkerQualification> wsQualifications);

    void deleteWorkshop(int wsId);



}
