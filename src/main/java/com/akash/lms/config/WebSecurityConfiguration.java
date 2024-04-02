package com.akash.lms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfiguration {
	
	
	@Bean
	public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(authz -> authz
						.requestMatchers("/auth/**").permitAll()
						.requestMatchers("/library/**").authenticated().anyRequest().permitAll()
						)
				.csrf(Customizer.withDefaults())
				.httpBasic(Customizer.withDefaults())
				.build();
	}


	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
