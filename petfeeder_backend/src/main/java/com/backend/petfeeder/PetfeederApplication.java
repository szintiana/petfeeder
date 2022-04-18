package com.backend.petfeeder;

import com.backend.petfeeder.Utils.JwtAuthorisationFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class PetfeederApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(PetfeederApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
					.addFilterAfter(new JwtAuthorisationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests()
					.antMatchers( "/login/**").permitAll()
					.antMatchers("/addUser").permitAll()
					.anyRequest().authenticated();
			http.cors();
		}
	}
}
