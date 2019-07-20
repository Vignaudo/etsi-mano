package com.ubiqube.etsi.mano;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.context.annotation.Bean;

import com.mongodb.MongoClient;

@SpringBootApplication
@EnableJms
public class Application extends SpringBootServletInitializer {

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public @Bean MongoClient mongoClient() {
		return new MongoClient("192.168.1.12");
	}
}
