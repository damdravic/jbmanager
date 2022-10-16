package com.anaximel.bmanager.appsecurity.repository;

import com.anaximel.bmanager.appsecurity.domain.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShelfRepository extends JpaRepository <Shelf, Integer> {

      Shelf findShelfByShelfName(String shelfName);

      List<Shelf> findAll();

      Shelf findShelfByShelfId(int id);
}
