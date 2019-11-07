package com.springBoot.example.sprinBootManager.service;

import com.springBoot.example.sprinBootManager.dao.UserRoleDAO;
import com.springBoot.example.sprinBootManager.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    UserRoleDAO userRoleDAO;

    @Autowired
    public UserRoleServiceImpl(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
    }

    @Override
    public void add(UserRole userRole) {
        userRoleDAO.add(userRole);
    }

    @Override
    public List<UserRole> getAll() {
        return userRoleDAO.getAll();
    }

    @Override
    public UserRole getById(int id) {
        return userRoleDAO.getById(id);
    }

    @Override
    public void update(UserRole userRole) {
        userRoleDAO.update(userRole);
    }

    @Override
    public void remove(UserRole userRole) {
        userRoleDAO.remove(userRole);
    }

    @Override
    public UserRole getUserRoleByUserRoleName(String userRoleName) {
        return userRoleDAO.getUserRoleByUserRoleName(userRoleName);
    }
}
