package com.example.bookservice.repository;

import com.example.bookservice.common.entities.User;
import com.example.bookservice.common.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

//@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findById(long id);
    boolean existsByUserName(String username);
}
