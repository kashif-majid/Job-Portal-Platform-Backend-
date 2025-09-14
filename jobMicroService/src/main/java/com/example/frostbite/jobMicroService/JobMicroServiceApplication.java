package com.example.frostbite.jobMicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class JobMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobMicroServiceApplication.class, args);
	}

}
