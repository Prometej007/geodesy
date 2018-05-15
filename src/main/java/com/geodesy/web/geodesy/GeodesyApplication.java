package com.geodesy.web.geodesy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class GeodesyApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeodesyApplication.class, args);
	}
}
