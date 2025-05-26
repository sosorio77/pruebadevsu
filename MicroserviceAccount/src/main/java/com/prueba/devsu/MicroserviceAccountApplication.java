package com.prueba.devsu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceAccountApplication.class, args);
	}

}

