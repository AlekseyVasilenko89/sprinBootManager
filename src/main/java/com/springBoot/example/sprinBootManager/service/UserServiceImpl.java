package com.springBoot.example.sprinBootManager.service;

import com.springBoot.example.sprinBootManager.dao.UserDAO;
import com.springBoot.example.sprinBootManager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserDAO userDAO;
    private PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, PasswordEncoder encoder) {
        this.userDAO = userDAO;
        this.encoder = encoder;
    }

    @Override
    public void add(User user) {
        String password = encoder.encode(user.getPassword());
        user.setPassword(password);
        userDAO.add(user);
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public User getById(int id) {
        return userDAO.getById(id);
    }

    @Override
    public void update(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userDAO.update(user);
    }

    @Override
    public void remove(User user) {
        userDAO.remove(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.getUserByUsername(username);
    }

    @Override
    public User getUserByName(String name) {
        return userDAO.getUserByUsername(name);
    }
}
