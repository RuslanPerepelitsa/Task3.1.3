package com.example.task313.service;

import com.example.task313.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();
    User addUser(User user);
    User getUserById(long id);
    User updateUser(User user);
    void removeUser(long id);
    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}
