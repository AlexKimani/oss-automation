package com.safaricom.hackathon.ossautomation.repository;

import com.safaricom.hackathon.ossautomation.pojo.UserIdentifier;
import com.safaricom.hackathon.ossautomation.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findUserByUsername(String username);

    Optional<Users> findUsersByUserCode(UserIdentifier userCode);
}
