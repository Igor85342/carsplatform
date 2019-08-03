package ru.moskalets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.moskalets.model.UserSpring;
import ru.moskalets.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserSpring findByLogin(String login) {
       return userRepository.findByLogin(login);
    }
}