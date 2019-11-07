package com.springBoot.example.sprinBootManager.dao;

import com.springBoot.example.sprinBootManager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    public User getById(int id) {
        User user;
        user = entityManager.find(User.class, id);
        user.getId();
        return user;
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void remove(User user) {
        entityManager.remove(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return (User) entityManager.createQuery(
                "from User where name = :username")
                .setParameter("username", username)
                .getSingleResult();
    }
}
