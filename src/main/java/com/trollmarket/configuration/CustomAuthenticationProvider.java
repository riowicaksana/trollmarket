package com.trollmarket.configuration;

import com.trollmarket.entity.Account;
import com.trollmarket.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AccountService accountService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = (authentication.getPrincipal() == null)? "NONE_PROVIDED" : authentication.getName();

        if(StringUtils.isEmpty(username)){
            throw new BadCredentialsException("invalid username");
        }

        UserDetails user = null;

        try{
            user = accountService.loadUserByUsername(username);
        }catch(UsernameNotFoundException exception){
            throw new BadCredentialsException("invalid login details");
        }
        System.out.println(request.getParameter("role"));
        System.out.println(user.getAuthorities().toArray()[0]);
        if (!user.getAuthorities().toArray()[0].toString().equals(request.getParameter("role"))) {
//            System.out.println("MASUK");
            throw new BadCredentialsException("Username with chosen role doesn't exist");
        }
        if (!passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
            throw new BadCredentialsException("Invalid Password");
        }


        return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
