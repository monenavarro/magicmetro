package com.magicmetro.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan(basePackages = "com.magicmetro.entity")
@EnableJpaRepositories(basePackages = "com.magicmetro.persistence")
@SpringBootApplication(scanBasePackages = "com.magicmetro")
public class StationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StationServiceApplication.class, args);
	}

}
