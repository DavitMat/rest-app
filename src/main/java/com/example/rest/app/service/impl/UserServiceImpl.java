package com.example.rest.app.service.impl;

import com.example.rest.app.enums.UserType;
import com.example.rest.app.model.User;
import com.example.rest.app.repository.UserRepository;
import com.example.rest.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        List<User>users = userRepository.findAll();
        return users;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User minTicketCount(UserType userType) {
        List<User> users = userRepository.getAllByUserType(userType);
        List<Integer> counts = users.stream().map(user -> user.getListTicket().size()).collect(Collectors.toList());
        int minIndex = counts.indexOf(Collections.min(counts));
        return users.get(minIndex);
    }
}
