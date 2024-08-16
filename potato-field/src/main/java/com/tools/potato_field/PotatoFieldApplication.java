package com.tools.potato_field;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan(basePackages = "com.tools.potato_field")
public class PotatoFieldApplication {

	public static void main(String[] args) {
		SpringApplication.run(PotatoFieldApplication.class, args);
	}
}