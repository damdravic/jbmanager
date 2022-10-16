package com.anaximel.bmanager.appsecurity.service;

import com.anaximel.bmanager.appsecurity.domain.Shelf;

import java.util.List;

public interface ShelfService {

    Shelf getShelf(String shelfName);

    List<Shelf> getAllShelves ();

    Shelf addNewShelf( String name,String location, int nor,int noc);

    Shelf updateShelf(String name,String location, int nor,int noc);

    void deleteShelf(int shelfId);




}
