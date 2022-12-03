package com.anaximel.bmanager.appsecurity.domain;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class ShelfCell {

    private int idShelfCell;

    private int rowIndex;

    private int colIndex;

    private ShelfCustomer shelfCustomer;

    private Date date;




}
