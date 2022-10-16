package com.anaximel.bmanager.appsecurity.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="shelf")
public class Shelf implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="shelf_id",nullable=false,updatable = false)
    private int shelfId;

    private String shelfName;

    private String location;

    private int noOfRows;

    private int noOfCols;


    public int getShelfId() {
        return shelfId;
    }

    public void setShelfId(int shelfId) {
        this.shelfId = shelfId;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNoOfRows() {
        return noOfRows;
    }

    public void setNoOfRows(int noOfRows) {
        this.noOfRows = noOfRows;
    }

    public int getNoOfCols() {
        return noOfCols;
    }

    public void setNoOfCols(int noOfCols) {
        this.noOfCols = noOfCols;
    }

    @Override
    public String toString() {
        return "Shelf{" +
                "shelfId=" + shelfId +
                ", shelfName='" + shelfName + '\'' +
                ", location='" + location + '\'' +
                ", noOfRows=" + noOfRows +
                ", noOfCols=" + noOfCols +
                '}';
    }
}
