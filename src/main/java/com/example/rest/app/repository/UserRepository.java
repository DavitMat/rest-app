package com.example.rest.app.repository;

import com.example.rest.app.enums.UserType;
import com.example.rest.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findById(Long id);
    List<User> getAllByUserType(UserType userType);
}
