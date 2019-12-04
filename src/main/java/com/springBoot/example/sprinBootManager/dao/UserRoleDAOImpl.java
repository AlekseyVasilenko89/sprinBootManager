package com.springBoot.example.sprinBootManager.dao;

import com.springBoot.example.sprinBootManager.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserRoleDAOImpl implements UserRoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserRoleDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(UserRole userRole) {
        entityManager.persist(userRole);
    }

    @Override
    public List getAll() {
        List<UserRole> users = null;
        return entityManager.createQuery("from UserRole").getResultList();

    }

    @Override
    public UserRole getById(int id) {
        UserRole userRole;
        userRole = entityManager.find(UserRole.class, id);
        userRole.getId();
        return userRole;
    }

    @Override
    public void update(UserRole userRole) {
        entityManager.merge(userRole);
    }

    @Override
    public void remove(UserRole userRole) {
        entityManager.remove(userRole);
    }

    @Override
    public UserRole getUserRoleByUserRoleName(String roleName) {
        return (UserRole) entityManager.createNativeQuery("select * from roles where role_name = :roleName", UserRole.class)
                .setParameter("roleName", roleName).getResultList();
    }
}
