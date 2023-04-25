package com.academia.ikub.spring.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RealEstateAgencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealEstateAgencyApplication.class, args);
	}

}
