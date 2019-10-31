package com.springBoot.example.sprinBootManager.dao;

import com.springBoot.example.sprinBootManager.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = null;
        Session session = sessionFactory.getCurrentSession();
        users = session.createQuery("from User").getResultList();
        return users;
    }

    @Override
    public User getById(int id) {
        User user;
        Session session = sessionFactory.getCurrentSession();
        user = session.get(User.class, id);
        user.getId();
        return user;
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public void remove(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }

    @Override
    public User getUserByUsername(String username) {
        User user = sessionFactory.getCurrentSession().createNativeQuery("select * from users where name = :username", User.class)
                .setParameter("username", username).getSingleResult();
        System.out.println(username + user.toString());
        return user;
    }
}
