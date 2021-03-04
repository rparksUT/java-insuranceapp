package com.capstone.insurancejavaapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InsurancejavaappApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsurancejavaappApplication.class, args);
		Database.loadData();
	}

}
