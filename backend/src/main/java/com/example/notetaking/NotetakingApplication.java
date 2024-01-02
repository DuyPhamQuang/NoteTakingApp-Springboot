package com.example.notetaking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.example.notetaking"})
public class NotetakingApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotetakingApplication.class, args);
	}

}
