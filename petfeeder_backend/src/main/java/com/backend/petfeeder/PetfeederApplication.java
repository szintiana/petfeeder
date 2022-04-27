package com.backend.petfeeder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetfeederApplication {
	public static void main(String[] args) {
		SpringApplication.run(PetfeederApplication.class, args);
	}
//
//	@Override
//	public void run(String... args) throws Exception {
//	}
//
//	@EnableWebSecurity
//	@Configuration
//	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			http.csrf().disable()
//					.addFilterAfter(new JwtAuthorisationFilter(), UsernamePasswordAuthenticationFilter.class)
//					.authorizeRequests()
////					.antMatchers("**").permitAll()
//					.antMatchers( "/login/**").permitAll()
//					.antMatchers("/addUser").permitAll()
//					.anyRequest().authenticated();
//			http.cors();
//		}
//	}
}
