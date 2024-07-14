package com.example.bookservice.repository;

import com.example.bookservice.common.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

//@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String login);

//    Optional<User> findByLogin(String userName);

}
