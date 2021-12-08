package com.example.facSchedule;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class FacScheduleApplication{



	public static void main(String[] args) {
		SpringApplication.run(FacScheduleApplication.class, args);
	}



}
