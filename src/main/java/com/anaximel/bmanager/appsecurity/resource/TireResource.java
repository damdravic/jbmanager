package com.anaximel.bmanager.appsecurity.resource;

import com.anaximel.bmanager.appsecurity.domain.Shelf;
import com.anaximel.bmanager.appsecurity.service.ShelfService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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
                                            @RequestParam("noc") String noc,
                                            @RequestParam("nor") String nor)  {

        int intNoc = Integer.parseInt(noc);

        Shelf shelf = this.shelfService.addNewShelf(shelfName,location,Integer.parseInt(nor),Integer.parseInt(noc));
        return new ResponseEntity<>(shelf, HttpStatus.OK);
    }

    @GetMapping("/getList")
    public ResponseEntity<List<Shelf>> getAll(){

        List<Shelf> shelves =this.shelfService.getAllShelves();


        return new ResponseEntity<>(shelves, HttpStatus.OK);
    }



}
