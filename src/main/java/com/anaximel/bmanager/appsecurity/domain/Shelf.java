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

    @Column(name="no_of_rows")
    private int nor;


    @Column(name="no_of_cols")
    private int noc;







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

    public int getNor() {
        return nor;
    }

    public void setNor(int nor) {
        this.nor = nor;
    }

    public int getNoc() {
        return noc;
    }

    public void setNoc(int noc) {
        this.noc = noc;
    }

    @Override
    public String toString() {
        return "Shelf{" +
                "shelfId=" + shelfId +
                ", shelfName='" + shelfName + '\'' +
                ", location='" + location + '\'' +
                ", noOfRows=" + nor +
                ", noOfCols=" + noc +
                '}';
    }
}
