package com.springBoot.example.sprinBootManager.dao;

import com.springBoot.example.sprinBootManager.model.UserRole;

import java.util.List;

public interface UserRoleDAO {

    void add(UserRole userRole);

    List getAll();

    UserRole getById(int id);

    void update(UserRole userRole);

    void remove(UserRole userRole);

    UserRole getUserRoleByUserRoleName(String name);
}
