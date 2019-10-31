package com.springBoot.example.sprinBootManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import preProject.dao.UserRoleDAO;
import preProject.model.UserRole;

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
    public List<UserRole> getUserRoleByUserRoleName(String userRoleName) {
        return userRoleDAO.getUserRoleByUserRoleName(userRoleName);
    }

    @Override
    public List<UserRole> getUserRoleByUserRoleName(String userRoleName1, String userRoleName2) {
        return userRoleDAO.getUserRoleByUserRoleName(userRoleName1, userRoleName2);
    }
}
