package com.anaximel.bmanager.appsecurity.repository;

import com.anaximel.bmanager.appsecurity.domain.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;

import java.util.List;

public interface WorkOrderRepositoryInterface extends JpaRepository <WorkOrder, Long>{

    WorkOrder  findWorkOrderById(long id);

    @NonNull
    List<WorkOrder> findAll();


}
