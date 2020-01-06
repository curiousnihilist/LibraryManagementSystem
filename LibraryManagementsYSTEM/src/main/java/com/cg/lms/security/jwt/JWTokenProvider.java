package com.cg.lms.security.jwt;

import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.cg.lms.dto.Role;
import com.cg.lms.security.exception.InvalidCredentialsException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTokenProvider {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	private static String HIDDEN_KEY = "d0of-Eq1c3r";

	private static final long TOKEN_VALIDITY = 3600 * 1000; // Validity in milli-seconds

	@PostConstruct
	protected void init() {
		HIDDEN_KEY = Base64.getEncoder().encodeToString(HIDDEN_KEY.getBytes());
	}

	public String createToken(String emailId, Role role) {
		Claims claims = Jwts.claims().setSubject(emailId); // Setting the subject using emailId to be used later on.
		claims.put("auth", new SimpleGrantedAuthority(role.getAuthority()));

		Date now = new Date();
		Date validity = new Date(now.getTime() + TOKEN_VALIDITY);

		return Jwts.builder()//
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, HIDDEN_KEY)
				.compact();
	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(HIDDEN_KEY).parseClaimsJws(token).getBody().getSubject();
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null) {
			return bearerToken;
		}
		return null;
	}

	public boolean validateToken(String token) throws InvalidCredentialsException {
		try {
			Jwts.parser().setSigningKey(HIDDEN_KEY).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			throw new InvalidCredentialsException("Invalid Access, Expired Session.\nPlease login again.");
		}
	}


}
