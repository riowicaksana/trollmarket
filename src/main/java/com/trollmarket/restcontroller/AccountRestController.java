package com.trollmarket.restcontroller;


import com.trollmarket.configuration.JwtToken;
import com.trollmarket.dto.account.RequestTokenDTO;
import com.trollmarket.dto.account.ResponseTokenDTO;
import com.trollmarket.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class AccountRestController {


    private AccountService accountService;

    private UserDetailsService userDetailsService;

    private JwtToken jwtToken;

    private AuthenticationManager authenticationManager;

    @Autowired
    public AccountRestController(AccountService accountService, UserDetailsService userDetailsService, JwtToken jwtToken, AuthenticationManager authenticationManager) {
        this.accountService = accountService;
        this.userDetailsService = userDetailsService;
        this.jwtToken = jwtToken;
        this.authenticationManager = authenticationManager;
    }
    @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseTokenDTO post(@RequestBody RequestTokenDTO dto) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
        } catch (Exception exception) {
            System.out.println("Can not authenticate!");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not authenticate", exception);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUsername());
        System.out.println("userDetails: " + userDetails.getUsername() + ", " + userDetails.getPassword());

        String role = accountService.getAccountRole(dto.getUsername());
        String token = jwtToken.generateToken(dto.getSubject(),dto.getUsername(), dto.getSecretKey(), role, dto.getAudience());
        ResponseTokenDTO response = new ResponseTokenDTO(dto.getUsername(), role, token);
        return response;
    }
}
