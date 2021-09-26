package com.anaximel.bmanager.appsecurity.repository;

import com.anaximel.bmanager.appsecurity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {


    User findUserByUsername(String username);
    User findUserByEmail(String email);


}
