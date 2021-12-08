package com.example.facSchedule;

import com.example.mybannerstarter.service.MyBannerService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class FacScheduleApplication  implements CommandLineRunner {

	@Autowired
	MyBannerService service;

	public static void main(String[] args) {
		SpringApplication.run(FacScheduleApplication.class, args);
	}

	@Override
	public void run(String...strings) throws Exception {
		service.hello("Hello/////////////////////////////////////////////////////////////////////////////////////");
	}
}
