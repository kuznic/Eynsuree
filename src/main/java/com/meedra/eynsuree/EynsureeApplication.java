package com.meedra.eynsuree;

/**
 * This is the Main Class.
 * Run this to start the application
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.meedra.eynsuree"})
public class EynsureeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EynsureeApplication.class, args);
	}

}
