package com.anaximel.bmanager.appsecurity.repository;

import com.anaximel.bmanager.appsecurity.domain.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;

import java.util.List;

public interface WorkshopRepositoryInterface extends JpaRepository<Workshop,Integer> {


    Workshop findWorkshopByWsId(int id);

    @NonNull
    List<Workshop> findAll();

}
