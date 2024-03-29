package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com"})
@SpringBootApplication
public class ThymeLeafWebSimpleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThymeLeafWebSimpleApplication.class, args);
	}

}
