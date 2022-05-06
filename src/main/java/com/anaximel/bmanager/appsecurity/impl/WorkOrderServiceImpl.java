package com.anaximel.bmanager.appsecurity.impl;

import com.anaximel.bmanager.appsecurity.domain.WorkOrder;
import com.anaximel.bmanager.appsecurity.repository.WorkOrderRepositoryInterface;
import com.anaximel.bmanager.appsecurity.service.WorkOrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WorkOrderServiceImpl implements WorkOrderServiceInterface {

   private WorkOrderRepositoryInterface workOrderRepositoryInterface;

   @Autowired
   WorkOrderServiceImpl(WorkOrderRepositoryInterface workOrderRepositoryInterface){
       this.workOrderRepositoryInterface = workOrderRepositoryInterface;
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
