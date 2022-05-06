package com.anaximel.bmanager.appsecurity.service;

import com.anaximel.bmanager.appsecurity.domain.Workshop;

import java.util.List;

public interface WorkshopServiceInterface {

    Workshop getWorkshopId(int id);

    List<Workshop> getAllWorkshops();


}
