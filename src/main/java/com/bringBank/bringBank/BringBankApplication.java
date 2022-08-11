package com.bringBank.bringBank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/** * This is A spring-boot application that looks-up for Camel routes in the given package  */
@SpringBootApplication
@ComponentScan(basePackages = "com.bringBank.bringBank")
public class BringBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BringBankApplication.class, args);
	}

}
