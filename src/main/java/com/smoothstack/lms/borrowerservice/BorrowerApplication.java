package com.smoothstack.lms.borrowerservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.context.annotation.Import;

import com.smoothstack.lms.borrowerservice.swagger.SwaggerConfig;


@SpringBootApplication
// @EnableDiscoveryClient
@Import({ SwaggerConfig.class })
public class BorrowerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BorrowerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("###################################################\n\n");
	}
}
