package com.anaximel.bmanager.appsecurity.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Worker extends User implements Serializable {

    private User wUser;

    private String qualification;

    public Worker(User wUser, String qualification) {

        this.wUser = wUser;
        this.qualification = qualification;
    }



    public User getWUser() {
        return wUser;
    }

    public void setWUser(User wUser) {
        this.wUser = wUser;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
