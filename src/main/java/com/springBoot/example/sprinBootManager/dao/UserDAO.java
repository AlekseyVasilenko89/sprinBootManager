package com.springBoot.example.sprinBootManager.dao;

import com.springBoot.example.sprinBootManager.model.User;

import java.util.List;

public interface UserDAO {

    void add(User user);

    List getAll();

    User getById(int id);

    void update(User user);

    void remove(User user);

    User getUserByUsername(String name);

}
