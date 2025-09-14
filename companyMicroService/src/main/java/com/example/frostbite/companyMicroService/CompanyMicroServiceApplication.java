package com.example.frostbite.companyMicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CompanyMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyMicroServiceApplication.class, args);
	}

}
