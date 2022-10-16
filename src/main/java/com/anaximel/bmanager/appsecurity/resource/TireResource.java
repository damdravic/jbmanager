package com.anaximel.bmanager.appsecurity.resource;

import com.anaximel.bmanager.appsecurity.domain.Shelf;
import com.anaximel.bmanager.appsecurity.service.ShelfService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path={"/tireWarehouse"})
@CrossOrigin(origins = "*")
public class TireResource {

    private ShelfService shelfService;

    public TireResource(ShelfService shelfService){
        this.shelfService = shelfService;
    }

    @PostMapping("/add")
    public ResponseEntity<Shelf> addNewShelf(@RequestParam("shelfName") String shelfName,
                                            @RequestParam("location") String location,
                                            @RequestParam("noc") int noc,
                                            @RequestParam("nor") int nor) throws IOException {

        Shelf shelf = this.shelfService.addNewShelf(shelfName,location,nor,noc);
        return new ResponseEntity<>(shelf, HttpStatus.OK);
    }



}
