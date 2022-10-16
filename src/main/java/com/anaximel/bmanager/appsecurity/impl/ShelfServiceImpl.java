package com.anaximel.bmanager.appsecurity.impl;

import com.anaximel.bmanager.appsecurity.domain.Shelf;
import com.anaximel.bmanager.appsecurity.repository.ShelfRepository;
import com.anaximel.bmanager.appsecurity.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ShelfServiceImpl implements ShelfService {

    private ShelfRepository shelfRepository;

    @Autowired
    public ShelfServiceImpl (ShelfRepository shelfRepository){
        this.shelfRepository = shelfRepository;
    }



    @Override
    public Shelf getShelf(String shelfName) {

         Shelf shelf = this.shelfRepository.findShelfByShelfName(shelfName);

        return shelf;
    }

    @Override
    public List<Shelf> getAllShelves() {
        return this.shelfRepository.findAll();
    }

    @Override
    public Shelf addNewShelf(String name,String location, int nor,int noc) {

         Shelf newShelf = new Shelf();
           newShelf.setShelfName(name);
           newShelf.setLocation(location);
           newShelf.setNoOfRows(nor);
           newShelf.setNoOfCols(noc);

        this.shelfRepository.save(newShelf);


        return newShelf;
    }

    @Override
    public Shelf updateShelf(String name,String location, int nor,int noc) {
        Shelf newShelf = new Shelf();
        newShelf.setShelfName(name);
        newShelf.setLocation(location);
        newShelf.setNoOfRows(nor);
        newShelf.setNoOfCols(noc);

        this.shelfRepository.save(newShelf);


        return newShelf;
    }

    @Override
    public void deleteShelf(int shelfId) {

          Shelf shelf = this.shelfRepository.findShelfByShelfId(shelfId);
 
        this.shelfRepository.delete(shelf);

    }
}
