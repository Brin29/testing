package com.brienn.testing.services;

import com.brienn.testing.entity.User;

import java.util.List;

public interface IUserService {

    List<User> getAllUser();

    User getUserById(Long id);

    User addUser(User newUser);

    User updateUser(Long id, User newUser);

    void deleteUser(Long id);

}