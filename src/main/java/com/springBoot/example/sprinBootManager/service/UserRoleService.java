package com.springBoot.example.sprinBootManager.service;

import com.springBoot.example.sprinBootManager.model.UserRole;

import java.util.List;

public interface UserRoleService {

    void add(UserRole user);

    List<UserRole> getAll();

    UserRole getById(int id);

    void update(UserRole user);

    void remove(UserRole user);

    UserRole getUserRoleByUserRoleName(String userRoleName);

}
