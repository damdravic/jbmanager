package com.anaximel.bmanager.appsecurity.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class WorkOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable =false, updatable = false)
    @JsonProperty(access =JsonProperty.Access.WRITE_ONLY)
    private Long id;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable=false,updatable = false,unique = true)
    private Long workOrderNr;

    private Car car;
    private Workshop workshop;
    private String description;
    private String parts;
    private String insurer;
    private Date createdDate;
    private Date dataIn;
    private Date dataOut;
    private Date scheduledDataIn;
    private Date scheduledDataOut;
    private int progress;
    private User createdBy;


    public WorkOrder() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkOrderNr() {
        return workOrderNr;
    }

    public void setWorkOrderNr(Long workOrderNr) {
        this.workOrderNr = workOrderNr;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParts() {
        return parts;
    }

    public void setParts(String parts) {
        this.parts = parts;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDataIn() {
        return dataIn;
    }

    public void setDataIn(Date dataIn) {
        this.dataIn = dataIn;
    }

    public Date getDataOut() {
        return dataOut;
    }

    public void setDataOut(Date dataOut) {
        this.dataOut = dataOut;
    }

    public Date getScheduledDataIn() {
        return scheduledDataIn;
    }

    public void setScheduledDataIn(Date scheduledDataIn) {
        this.scheduledDataIn = scheduledDataIn;
    }

    public Date getScheduledDataOut() {
        return scheduledDataOut;
    }

    public void setScheduledDataOut(Date scheduledDataOut) {
        this.scheduledDataOut = scheduledDataOut;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public WorkOrder(Car car, Workshop workshop, String description, String parts,String insurer, Date dataIn, Date dataOut, Date scheduledDataIn, Date scheduledDataOut, User createdBy) {
        this.car = car;
        this.workshop = workshop;
        this.description = description;
        this.parts = parts;
        this.insurer = insurer;
        this.dataIn = dataIn;
        this.dataOut = dataOut;
        this.scheduledDataIn = scheduledDataIn;
        this.scheduledDataOut = scheduledDataOut;

        this.createdBy = createdBy;
    }





    public WorkOrder(Long id, Long workOrderNr, Car car, Workshop workshop,
                     String description, String parts, Date createdDate,
                     Date dataIn, Date dataOut, Date scheduledDataIn,
                     Date scheduledDataOut, int progress, User createdBy) {
        this.id = id;
        this.workOrderNr = workOrderNr;
        this.car = car;
        this.workshop = workshop;
        this.description = description;
        this.parts = parts;
        this.createdDate = createdDate;
        this.dataIn = dataIn;
        this.dataOut = dataOut;
        this.scheduledDataIn = scheduledDataIn;
        this.scheduledDataOut = scheduledDataOut;
        this.progress = progress;
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "WorkOrder{" +
                "id=" + id +
                ", workOrderNr=" + workOrderNr +
                ", car=" + car +
                ", workshop=" + workshop +
                ", description='" + description + '\'' +
                ", parts='" + parts + '\'' +
                ", createdDate=" + createdDate +
                ", dataIn=" + dataIn +
                ", dataOut=" + dataOut +
                ", scheduledDataIn=" + scheduledDataIn +
                ", scheduledDataOut=" + scheduledDataOut +
                ", progress=" + progress +
                ", createdBy=" + createdBy +
                '}';
    }
}
