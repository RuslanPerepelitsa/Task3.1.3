package com.example.task313.service;

import com.example.task313.model.Role;
import com.example.task313.repository.RoleDao;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.allRole();
    }

    @Override
    public Role getRoleById(Long id) {
        return roleDao.findRoleById(id);
    }

    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

}
