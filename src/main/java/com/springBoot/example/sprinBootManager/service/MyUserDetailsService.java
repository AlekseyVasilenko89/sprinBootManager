package com.springBoot.example.sprinBootManager.service;

import com.springBoot.example.sprinBootManager.model.MyUserDetails;
import com.springBoot.example.sprinBootManager.model.User;
import com.springBoot.example.sprinBootManager.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public MyUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> optionalUser = usersRepository.findByName(userName);

        optionalUser
                .orElseThrow(() -> new UsernameNotFoundException("User name not Found"));
        return optionalUser
                .map(MyUserDetails::new).get();
    }
}
