package com.cg.lms.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired private JWTokenProvider jwTokenProvider;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// Disable CSRF (cross site request forgery)
		httpSecurity.csrf().disable();

		// No session will be created or used by spring security
		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Entry points
		httpSecurity.authorizeRequests()//
				.antMatchers("/auth-api/authenticate").permitAll() // Allowing anyone to access the login page
				.antMatchers("/auth-api/sign-up").permitAll()// Allowing anyone to access the sign up page.
				.antMatchers("/recipe-api/fetch-all").permitAll() // Allowing anyone to access the Fetch All Recipe end-point
				// Disallow everything else..
				.anyRequest().authenticated();

		// If a user try to access a resource without having enough permissions
		httpSecurity.exceptionHandling().accessDeniedPage("/auth-api/authenticate");

		// Apply JWT
		httpSecurity.apply(new JWTokenFilterConfigurer(jwTokenProvider));
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	
	

}
