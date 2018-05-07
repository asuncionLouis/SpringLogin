package com.spring;

import java.util.Base64;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.model.User;
import com.spring.repository.UserRepository;

@SpringBootApplication
public class SpringLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringLoginApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(UserRepository userRepo) {

		return args -> {
			String authStr = Base64.getEncoder().encodeToString(("admin:admin").getBytes());
			userRepo.save(new User("admin", authStr));
		};
	}
}
