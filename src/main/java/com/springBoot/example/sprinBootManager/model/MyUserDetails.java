package com.springBoot.example.sprinBootManager.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails extends User implements UserDetails {

    private User user;

    private List<SimpleGrantedAuthority> auhorities = new LinkedList<>();

    public MyUserDetails(User user) {
        super();
        this.user = user;
        for (UserRole role : user.getUserRoles()) {
            auhorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return auhorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }
}
