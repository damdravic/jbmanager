package com.anaximel.bmanager.appsecurity.impl;

import com.anaximel.bmanager.appsecurity.domain.Workshop;
import com.anaximel.bmanager.appsecurity.repository.WorkshopRepositoryInterface;
import com.anaximel.bmanager.appsecurity.service.WorkshopServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WorkshopServiceImpl implements WorkshopServiceInterface {

   private WorkshopRepositoryInterface workshopRepositoryInterface;


   @Autowired
   WorkshopServiceImpl(WorkshopRepositoryInterface workshopRepositoryInterface) {
       this.workshopRepositoryInterface = workshopRepositoryInterface;

   }
    @Override
    public Workshop getWorkshopId(int id) {
        return this.workshopRepositoryInterface.findWorkshopById(id);
    }

    @Override
    public List<Workshop> getAllWorkshops() {
        return this.workshopRepositoryInterface.findAll();
    }
}
