package com.galicia.challenge.microservice.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceWeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceWeatherApplication.class, args);
	}

}
