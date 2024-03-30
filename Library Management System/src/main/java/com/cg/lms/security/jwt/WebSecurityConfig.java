package com.cg.lms.security.jwt;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;




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
				.antMatchers("/auth-api/get-user").permitAll() // Allowing anyone to access the Fetch All Recipe end-point
				.antMatchers("/library/bytitle/**").permitAll()
				.antMatchers("/library/getbyisbn/**").permitAll()
				.antMatchers("/library/getbycategory/**").permitAll()
				.antMatchers("/library/getbyauthor/**").permitAll()
				.antMatchers("/library/getallbooks").permitAll()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				// Disallow everything else..
				.anyRequest().authenticated();

		// If a user try to access a resource without having enough permissions
		httpSecurity.exceptionHandling().accessDeniedPage("/auth-api/authenticate");

		// Apply JWT
		httpSecurity.apply(new JWTokenFilterConfigurer(jwTokenProvider));
		
		// CORS configuration
		//httpSecurity.cors();
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
	
	
    
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        Arrays.asList(new String[] {"http://localhost:8880"});
        configuration.setAllowedOrigins(Arrays.asList(new String[] {"*"}));
        configuration.setAllowedMethods(Arrays.asList(new String[] {"*"}));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
	

}
