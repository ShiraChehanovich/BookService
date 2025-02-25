package com.example.bookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = {"com.example.bookservice"})
@EnableJpaRepositories(basePackages = "com.example.bookservice.repository")
@EntityScan(basePackages = "com.example.bookservice.common.entities")
@PropertySource("/.env")
public class BookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup(){

	}
}



