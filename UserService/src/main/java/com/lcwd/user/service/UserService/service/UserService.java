package com.lcwd.user.service.UserService.service;

import com.lcwd.user.service.UserService.Entities.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUser();

    User getUser(String userId);

}
