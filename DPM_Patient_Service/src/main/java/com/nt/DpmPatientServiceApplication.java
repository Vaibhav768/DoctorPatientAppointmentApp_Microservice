package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DpmPatientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DpmPatientServiceApplication.class, args);
	}

}
