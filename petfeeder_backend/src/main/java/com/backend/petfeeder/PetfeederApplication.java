package com.backend.petfeeder;

import com.backend.petfeeder.Controller.FeedDateController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetfeederApplication implements CommandLineRunner {
	@Autowired
	private FeedDateController feedDateController;

	public static void main(String[] args) {
		SpringApplication.run(PetfeederApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
