package com.example.rest.app.service;

import com.example.rest.app.enums.UserType;
import com.example.rest.app.model.User;

import java.util.List;

public interface UserService {

    User getById(Long id);

    void createUser(User user);

    List<User> findAll();

    void deleteById(Long id);

    User minTicketCount(UserType userType);
}