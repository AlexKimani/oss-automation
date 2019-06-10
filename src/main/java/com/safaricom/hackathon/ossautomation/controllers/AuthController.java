package com.safaricom.hackathon.ossautomation.controllers;

import com.safaricom.hackathon.ossautomation.pojo.UserIdentifier;
import com.safaricom.hackathon.ossautomation.repository.UsersRepository;
import com.safaricom.hackathon.ossautomation.security.jwt.JwtTokenProvider;
import com.safaricom.hackathon.ossautomation.web.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UsersRepository usersRepository;

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody AuthenticationRequest data) {

        try {
            System.out.println("You reached this line.....");
            String username = data.getUsername();
            // Create an encoder with strength 16
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
            String result = encoder.encode(data.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            UserIdentifier userIdentifier = new UserIdentifier();
            userIdentifier.setUserCode(username);
            String token = jwtTokenProvider.createToken(username, this.usersRepository.findUsersByUserCode(userIdentifier)
                    .orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}
