package com.example.task313.repository;

import com.example.task313.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext()
    EntityManager entityManager;

    @Override
    public List allRole() {
        return entityManager.createQuery(
                "from Role").getResultList();
    }

    @Override
    public Role findRoleById(Long id) throws NoSuchElementException {
        return entityManager.find(Role.class,id);
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

}
