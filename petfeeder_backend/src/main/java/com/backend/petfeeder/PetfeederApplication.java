package com.backend.petfeeder;

import com.backend.petfeeder.Service.PythonExecutorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetfeederApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(PetfeederApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PythonExecutorService.execute();
	}
}
