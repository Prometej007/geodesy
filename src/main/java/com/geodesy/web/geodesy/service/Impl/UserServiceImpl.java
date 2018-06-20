package com.geodesy.web.geodesy.service.Impl;

import com.geodesy.web.geodesy.model.User;
import com.geodesy.web.geodesy.repository.UserRepository;
import com.geodesy.web.geodesy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(String email, String password) {
        return userRepository.save(new User().setEmail(email).setPassword(passwordEncoder.encode(password)));
    }
}
