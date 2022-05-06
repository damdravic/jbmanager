package com.anaximel.bmanager.appsecurity.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Workshop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable= false,updatable = false)
    private int wsId;

    private String wsName;
    private String wsImage;
    private Boolean wsIsActive;
    private String wsDescription;
    private List<Worker> wsWorkers;
    private List<WorkerQualification> workerQualifications;

    public Workshop(int wsId, String wsName, String wsImage, Boolean wsIsActive, String wsDescription, List<Worker> wsWorkers, List<WorkerQualification> workerQualifications) {
        this.wsId = wsId;
        this.wsName = wsName;
        this.wsImage = wsImage;
        this.wsIsActive = wsIsActive;
        this.wsDescription = wsDescription;
        this.wsWorkers = wsWorkers;
        this.workerQualifications = workerQualifications;
    }

    public Workshop() {
    }

    public int getWsId() {
        return wsId;
    }

    public void setWsId(int wsId) {
        this.wsId = wsId;
    }

    public String getWsName() {
        return wsName;
    }

    public void setWsName(String wsName) {
        this.wsName = wsName;
    }

    public String getWsImage() {
        return wsImage;
    }

    public void setWsImage(String wsImage) {
        this.wsImage = wsImage;
    }

    public Boolean getWsIsActive() {
        return wsIsActive;
    }

    public void setWsIsActive(Boolean wsIsActive) {
        this.wsIsActive = wsIsActive;
    }

    public String getWsDescription() {
        return wsDescription;
    }

    public void setWsDescription(String wsDescription) {
        this.wsDescription = wsDescription;
    }
}
