package com.springBoot.example.sprinBootManager.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import preProject.model.UserRole;

import java.util.List;

@Repository
@Transactional
public class UserRoleDAOImpl implements UserRoleDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserRoleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(UserRole userRole) {
        Session session = sessionFactory.getCurrentSession();
        session.save(userRole);
    }

    @Override
    public List getAll() {
        List<UserRole> users = null;
        Session session = sessionFactory.getCurrentSession();
        users = session.createQuery("from UserRole").getResultList();
        return users;
    }

    @Override
    public UserRole getById(int id) {
        UserRole userRole;
        Session session = sessionFactory.getCurrentSession();
        userRole = session.get(UserRole.class, id);
        userRole.getId();
        return userRole;
    }

    @Override
    public void update(UserRole userRole) {
        Session session = sessionFactory.getCurrentSession();
        session.update(userRole);
    }

    @Override
    public void remove(UserRole userRole) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(userRole);
    }

    @Override
    public List<UserRole> getUserRoleByUserRoleName(String roleName) {
        return sessionFactory.getCurrentSession().createNativeQuery("select * from roles where role_name = :roleName", UserRole.class)
                .setParameter("roleName", roleName).getResultList();
    }

    @Override
    public List<UserRole> getUserRoleByUserRoleName(String roleName1, String roleName2) {
        List<UserRole> userRoles = null;
        UserRole userRoleResult1 = sessionFactory.getCurrentSession().createNativeQuery("select * from roles where role_name = :roleName", UserRole.class)
                .setParameter("roleName", roleName1).getSingleResult();
        UserRole userRoleResult2 = sessionFactory.getCurrentSession().createNativeQuery("select * from roles where role_name = :roleName", UserRole.class)
                .setParameter("roleName", roleName2).getSingleResult();
        userRoles.add(userRoleResult1);
        userRoles.add(userRoleResult2);
        return userRoles;
    }
}
