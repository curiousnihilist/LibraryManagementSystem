package com.cg.lms.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JWTokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>  {

	@Autowired
	private JWTokenProvider jwTokenProvider;
	
	public JWTokenFilterConfigurer(JWTokenProvider jwTokenProvider) {
		this.jwTokenProvider = jwTokenProvider;
	}

	@Override
	public void configure(HttpSecurity builder) throws Exception {
		JWTokenFilter customFilter = new JWTokenFilter(jwTokenProvider);
	    builder.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
