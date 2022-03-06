package com.formacionspring.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@SpringBootApplication
public class ApiRestCocheApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ApiRestCocheApplication.class, args);
	}
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	

	@Override
	public void run(String... args) throws Exception {
		String pass = "12345";
		for (int i = 0; i < 3; i++) {
			String passBcrypt = passwordEncoder.encode(pass);
			System.out.println(passBcrypt);
		}

	}


}
