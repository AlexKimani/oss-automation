package com.safaricom.hackathon.ossautomation.controllers;

import com.safaricom.hackathon.ossautomation.exception.ValidationException;
import com.safaricom.hackathon.ossautomation.pojo.UserIdentifier;
import com.safaricom.hackathon.ossautomation.pojo.Users;
import com.safaricom.hackathon.ossautomation.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "/users")
public class UsersController {
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping(path = "/all")
    public Page getAllUsers (Pageable pageable) {
        return usersRepository.findAll(pageable);
    }

    @GetMapping("/me")
    public ResponseEntity currentUser(@AuthenticationPrincipal UserDetails userDetails){
        Map<Object, Object> model = new HashMap<>();
        model.put("username", userDetails.getUsername());
        model.put("roles", userDetails.getAuthorities()
                .stream()
                .map(a -> ((GrantedAuthority) a).getAuthority())
                .collect(toList())
        );
        return ok(model);
    }

    @PostMapping(path = "/create")
    public Users createUser (@RequestBody Users users) {
        Users systemUser = usersRepository.findUserByUsername("system");
        String systemUserCode = systemUser.getUserCode().getUserCode();

        //Check is user exists
        Users existingUser = usersRepository.findUserByUsername(users.getUsername());
        if (existingUser.getSurname().matches(users.getSurname()) || users.getSurname().matches(systemUser.getSurname())) {
            throw  new ValidationException("User", "the user is already registered.");
        }

        //password encrypt using bcrypt
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = bCryptPasswordEncoder.encode(users.getPassword());

        UserIdentifier identifier = new UserIdentifier();
        identifier.setUserCode(users.getSurname());
        users.setPassword(users.getPassword());
        users.setUserCode(identifier);
        users.setRoles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
        users.setActive(1);
        users.setCreatedBy(systemUserCode);
        return usersRepository.save(users);
    }

    @GetMapping(path = "/findUser/{username}")
    public Users getUserByUserName(@PathVariable(value = "username") String username) {
        UserIdentifier userIdentifier = new UserIdentifier();
        userIdentifier.setUserCode(username);
        return usersRepository.findUsersByUserCode(userIdentifier).orElseThrow(()
                -> new UsernameNotFoundException("Username " + username + "not found"));
    }

    @PutMapping(path = "/update/{username}")
    public Users updateUserDetails(@PathVariable(value = "username") String username, Users users) {
        UserIdentifier userIdentifier = new UserIdentifier();
        userIdentifier.setUserCode(username);
        Users newUser = usersRepository.findUsersByUserCode(userIdentifier).orElseThrow(()
                -> new UsernameNotFoundException("Username " + username + "not found"));
        newUser.setRoles(users.getRoles());
        newUser.setPassword(users.getPassword());
        newUser.setSurname(users.getSurname());
        newUser.setEmail(users.getEmail());
        newUser.setOtherNames(users.getOtherNames());
        newUser.setMsisdn(users.getMsisdn());
        newUser.setUpdatedBy(username);

        return usersRepository.save(newUser);
    }
}
