package com.anaximel.bmanager.appsecurity.impl;

import com.anaximel.bmanager.appsecurity.domain.HttpResponse;
import com.anaximel.bmanager.appsecurity.domain.Worker;
import com.anaximel.bmanager.appsecurity.domain.WorkerQualification;
import com.anaximel.bmanager.appsecurity.domain.Workshop;
import com.anaximel.bmanager.appsecurity.repository.WorkshopRepositoryInterface;
import com.anaximel.bmanager.appsecurity.service.WorkshopServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class WorkshopServiceImpl implements WorkshopServiceInterface {

   private WorkshopRepositoryInterface workshopRepositoryInterface;


   @Autowired
   WorkshopServiceImpl(WorkshopRepositoryInterface workshopRepositoryInterface) {
       this.workshopRepositoryInterface = workshopRepositoryInterface;

   }
    @Override
    public Workshop getWorkshopId(int id) {
        return this.workshopRepositoryInterface.findWorkshopByWsId(id);
    }

    @Override
    public List<Workshop> getAllWorkshops() {
        return this.workshopRepositoryInterface.findAll();
    }

    @Override
    public Workshop addNewWorkshop(String wsName, String wsImage, boolean wsIsActive, String wsDescription, List<Worker> wsWorkers, List<WorkerQualification> wsQualifications) {
         Workshop workshop = new Workshop(wsName,wsImage,wsIsActive,wsDescription,wsWorkers,wsQualifications);

       return workshop;
    }

    @Override
    public void deleteWorkshop(int wsId) {
       Workshop delWorkshop = this.workshopRepositoryInterface.getOne(wsId);
       this.workshopRepositoryInterface.delete(delWorkshop);
    }




}
