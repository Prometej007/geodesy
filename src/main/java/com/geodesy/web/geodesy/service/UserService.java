package com.geodesy.web.geodesy.service;

import com.geodesy.web.geodesy.model.User;

public interface UserService {

    User save(String email, String password);

}
