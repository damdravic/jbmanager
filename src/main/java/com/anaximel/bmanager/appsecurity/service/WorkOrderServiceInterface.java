package com.anaximel.bmanager.appsecurity.service;

import com.anaximel.bmanager.appsecurity.domain.WorkOrder;

import java.util.List;


public interface WorkOrderServiceInterface {

    WorkOrder getOrderById( long id);

    List<WorkOrder> getAllOrders();


}
