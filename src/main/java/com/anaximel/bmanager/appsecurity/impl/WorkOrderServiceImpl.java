package com.anaximel.bmanager.appsecurity.impl;

import com.anaximel.bmanager.appsecurity.domain.Car;
import com.anaximel.bmanager.appsecurity.domain.User;
import com.anaximel.bmanager.appsecurity.domain.WorkOrder;
import com.anaximel.bmanager.appsecurity.domain.Workshop;
import com.anaximel.bmanager.appsecurity.repository.WorkOrderRepositoryInterface;
import com.anaximel.bmanager.appsecurity.service.WorkOrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class WorkOrderServiceImpl implements WorkOrderServiceInterface {

   private WorkOrderRepositoryInterface workOrderRepositoryInterface;

   @Autowired
   WorkOrderServiceImpl(WorkOrderRepositoryInterface workOrderRepositoryInterface){
       this.workOrderRepositoryInterface = workOrderRepositoryInterface;
   }

    public WorkOrderServiceImpl() {
    }

    @Override
    public WorkOrder addNewWorkOrder(Car car, Workshop workshop, String description, String parts,String insurer, Date dateIn, Date dateOut, Date scheduledDateIn, Date scheduledDateOut, User user) {

       WorkOrder newWorkOrder = new WorkOrder(car,workshop,description,parts,insurer,dateIn,dateOut,scheduledDateIn,scheduledDateOut,user);

       this.workOrderRepositoryInterface.save(newWorkOrder);
       return newWorkOrder;

    }

    @Override
    public WorkOrder getOrderById(long id) {
        return this.workOrderRepositoryInterface.findWorkOrderById(id);
    }

    @Override
    public List<WorkOrder> getAllOrders() {
        return this.workOrderRepositoryInterface.findAll();
    }
}
