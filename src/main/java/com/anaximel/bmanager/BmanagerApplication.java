package com.anaximel.bmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

import static com.anaximel.bmanager.appsecurity.constant.FileConstant.USER_FOLDER;

@SpringBootApplication
public class BmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BmanagerApplication.class, args);

		new File(USER_FOLDER).mkdirs();
	}



}
