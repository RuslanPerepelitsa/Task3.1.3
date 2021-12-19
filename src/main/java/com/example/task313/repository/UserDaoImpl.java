package com.example.task313.repository;

import com.example.task313.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext()
    EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery(
                "from User ", User.class).getResultList();
    }

    @Override
    public User addUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User updateUser(User user) {
        entityManager.merge(user);
        return user;
    }

    @Override
    public void removeUser(long id) {
        entityManager.createQuery("delete from User where id=:id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public User getUserByUsername(String email) {
        return entityManager.createQuery("select user from User user where user.email= :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

}
