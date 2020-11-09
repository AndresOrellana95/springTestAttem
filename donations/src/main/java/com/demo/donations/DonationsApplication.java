package com.demo.donations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(value = "com.demo.donations.model.repository")
@EntityScan(basePackages = "com.demo.donations.model.entity")
public class DonationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DonationsApplication.class, args);
	}

}
