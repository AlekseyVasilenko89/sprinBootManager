package com.springBoot.example.sprinBootManager.controller;

import com.springBoot.example.sprinBootManager.model.User;
import com.springBoot.example.sprinBootManager.service.UserRoleServiceImpl;
import com.springBoot.example.sprinBootManager.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/jjj")
public class UserRestController {

    private UserServiceImpl userService;
    private UserRoleServiceImpl userRoleService;

    @Autowired
    public UserRestController(UserServiceImpl userService, UserRoleServiceImpl userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @RequestMapping(value = "/rest/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = this.userService.getById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestBody @Valid User user) {
        HttpHeaders headers = new HttpHeaders();

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.userService.add(user);
        return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@RequestBody @Valid User user, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.userService.update(user);

        return new ResponseEntity<>(user, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteCustomer(@PathVariable("id") Integer id) {
        User user = this.userService.getById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.userService.remove(user);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/rest", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllCustomers() {
        List<User> customers = this.userService.getAll();

        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
