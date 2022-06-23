package com.anaximel.bmanager.appsecurity.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Insurer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Iid;

    private String name;


    public Insurer(int iid, String name) {
        Iid = iid;
        this.name = name;
    }

    public Insurer(String name) {
        this.name = name;
    }

    public Insurer() {
    }

    public int getIid() {
        return Iid;
    }

    public void setIid(int iid) {
        Iid = iid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
