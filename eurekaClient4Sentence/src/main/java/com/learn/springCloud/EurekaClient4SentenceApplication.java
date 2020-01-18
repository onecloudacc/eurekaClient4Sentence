package com.learn.springCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaClient4SentenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClient4SentenceApplication.class, args);
	}
}
