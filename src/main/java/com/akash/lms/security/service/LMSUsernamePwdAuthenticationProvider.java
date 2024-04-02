package com.akash.lms.security.service;

import com.akash.lms.enitities.Account;
import com.akash.lms.repo.AccountRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class LMSUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;

    public LMSUsernamePwdAuthenticationProvider(AccountRepository repo, PasswordEncoder encoder){
        this.accountRepository = repo;
        this.passwordEncoder = encoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        Account acc = accountRepository.findByEmail(email);
        if(acc == null){
            throw new BadCredentialsException("Account with email id:"+email+" is nor present!");
        }
        if(passwordEncoder.matches(pwd,acc.getPassword())){
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(acc.getRole().toString()));
            return new UsernamePasswordAuthenticationToken(email,pwd,authorities);
        }else{
            throw new BadCredentialsException("Invalid password!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
