package com.evangelism.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EvangelismApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvangelismApplication.class, args);
		System.out.println("Running...");
	}
}
