package com.anaximel.bmanager.appsecurity.service;

import com.anaximel.bmanager.appsecurity.domain.Car;
import com.anaximel.bmanager.appsecurity.domain.User;
import com.anaximel.bmanager.appsecurity.domain.WorkOrder;
import com.anaximel.bmanager.appsecurity.domain.Workshop;

import java.util.Date;
import java.util.List;


public interface WorkOrderServiceInterface {

    WorkOrder addNewWorkOrder(Car car,
                              Workshop workshop,
                              String description,
                              String parts,
                              String insurer,
                              Date dateIn,
                              Date dateOut,
                              Date scheduledDateIn,
                              Date scheduledDateOut,
                              User user);


    WorkOrder getOrderById( long id);

    List<WorkOrder> getAllOrders();


}
