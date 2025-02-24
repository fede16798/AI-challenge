package com.galicia.challenge.microservice.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class MicroserviceChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceChatApplication.class, args);
	}

}
