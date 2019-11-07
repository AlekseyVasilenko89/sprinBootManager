package com.springBoot.example.sprinBootManager.service;



import com.springBoot.example.sprinBootManager.model.User;

import java.util.List;

public interface UserService {

    void add(User user);

    List<User> getAll();

    User getById(int id);

    void update(User user);

    void remove(User user);

    User getUserByName(String name);
}
