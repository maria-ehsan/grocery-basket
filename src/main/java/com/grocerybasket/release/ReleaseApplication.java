package com.grocerybasket.release;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources(value = {@PropertySource("classpath:application.properties")})
@ComponentScan(basePackages = "com.grocerybasket")
public class ReleaseApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ReleaseApplication.class, args);
	}

}
