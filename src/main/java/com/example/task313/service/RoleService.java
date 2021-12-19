package com.example.task313.service;

import com.example.task313.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(Long id);
    void addRole(Role role);
}
