package com.example.task313.repository;

import com.example.task313.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();
    User addUser(User user);
    User getUserById(long id);
    User updateUser(User user);
    void removeUser(long id);
    User getUserByUsername(String username);

}