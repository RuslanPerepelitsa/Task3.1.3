package com.example.task313.repository;

import com.example.task313.model.Role;

import java.util.List;

public interface RoleDao {

    List<Role> allRole();
    Role findRoleById(Long id);
    void addRole(Role role);
}
