package com.mytech.thebagsportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mytech.thebagsportal", "com.mytech.thebagsservice"})
@EntityScan({"com.mytech.thebagsservice.entities"})
public class TheBagsPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheBagsPortalApplication.class, args);
	}

}