package com.springBoot.example.sprinBootManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class UserRole implements GrantedAuthority {

    private static final long serialVersionUID = 1;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_name")
    private String roleName;

//    @JsonIgnore
//    @ManyToMany()
//    @JoinTable(name = "users_roles",
//            joinColumns = @JoinColumn(name = "roles_id"),
//            inverseJoinColumns = @JoinColumn(name = "users_id"))
//    private List<User> users;//delete


    public UserRole() {
    }

    public UserRole(String roleName) {
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }

    public void setAuthority(String roleName) {
        this.roleName = roleName;
    }

//    public List<User> getUser() {
//        return users;
//    }
//
//    public void setUser(List<User> users) {
//        this.users = users;
//    }
}
