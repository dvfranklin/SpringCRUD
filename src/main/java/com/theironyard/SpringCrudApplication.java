package com.theironyard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringCrudApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SpringCrudApplication.class, args);
	}
}
