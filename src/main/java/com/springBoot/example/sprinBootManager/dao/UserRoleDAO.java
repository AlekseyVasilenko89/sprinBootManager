package com.springBoot.example.sprinBootManager.dao;

import preProject.model.UserRole;

import java.util.List;

public interface UserRoleDAO {

    void add(UserRole userRole);

    List getAll();

    UserRole getById(int id);

    void update(UserRole userRole);

    void remove(UserRole userRole);

    List<UserRole> getUserRoleByUserRoleName(String name);

    List<UserRole> getUserRoleByUserRoleName(String name1, String name2);
}
