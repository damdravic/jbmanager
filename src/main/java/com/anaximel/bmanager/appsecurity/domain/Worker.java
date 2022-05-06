package com.anaximel.bmanager.appsecurity.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Worker extends User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable =false,updatable = false)
    private int wUserId;


    private User wUser;
    private String qualification;

    public Worker(int wUserId, User wUser, String qualification) {
        this.wUserId = wUserId;
        this.wUser = wUser;
        this.qualification = qualification;
    }

    public int getwUserId() {
        return wUserId;
    }

    public void setwUserId(int wUserId) {
        this.wUserId = wUserId;
    }

    public User getwUser() {
        return wUser;
    }

    public void setwUser(User wUser) {
        this.wUser = wUser;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
