package com.safaricom.hackathon.ossautomation.security;

import com.safaricom.hackathon.ossautomation.pojo.UserIdentifier;
import com.safaricom.hackathon.ossautomation.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private UsersRepository usersRepository;

    public CustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserIdentifier userIdentifier = new UserIdentifier();
        userIdentifier.setUserCode(username);
        return this.usersRepository.findUsersByUserCode(userIdentifier)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }
}
