package com.springBoot.example.sprinBootManager.controller;

import com.springBoot.example.sprinBootManager.model.User;
import com.springBoot.example.sprinBootManager.model.UserRole;
import com.springBoot.example.sprinBootManager.service.UserRoleServiceImpl;
import com.springBoot.example.sprinBootManager.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private UserServiceImpl userService;
    private UserRoleServiceImpl userRoleService;

    @Autowired
    public UserController(UserServiceImpl userService, UserRoleServiceImpl userRoleService) {
        this.userRoleService = userRoleService;
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView welcomeUsers(ModelAndView modelAndView) {
        modelAndView.setViewName("welcome");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginUsers(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/showUsers", method = RequestMethod.GET)
    public ModelAndView allUser(ModelAndView modelAndView, Authentication authentication) {
        boolean isAdmin = false;
        boolean isUser = false;
        User principal = (User) authentication.getPrincipal();
        for (UserRole userRole : principal.getAuthorities()) {
            if (userRole.getAuthority().equalsIgnoreCase("admin")) {
                isAdmin = true;
            }
            if (userRole.getAuthority().equalsIgnoreCase("user")) {
                isUser = true;
            }
        }
        modelAndView.addObject("users", userService.getAll());
        modelAndView.addObject("userAuthorized", principal);
        modelAndView.setViewName("table");
        modelAndView.addObject("isAdmin", isAdmin);
        modelAndView.addObject("isUser", isUser);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/update/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(ModelAndView modelAndView, @PathVariable String id) {
        User user = userService.getById(Integer.parseInt(id));
        List<UserRole> userRoles = user.getAuthorities();
        modelAndView.setViewName("update-user");
        modelAndView.addObject("user", user);
        modelAndView.addObject("userRoles", userRoles);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/update/{id}", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute User user, @RequestParam(value = "roleAdmin", required = false) String userRole1,
                                 @RequestParam(value = "roleUser", required = false) String userRole2,
                                 ModelAndView modelAndView, @PathVariable String id) {
        List<UserRole> userRoles = new ArrayList<>();
        if (userRole1 != null) {
            UserRole userRoleResult1 = userRoleService.getById(Integer.parseInt(userRole1));
            userRoles.add(userRoleResult1);
        }
        if (userRole2 != null) {
            UserRole userRoleResult2 = userRoleService.getById(Integer.parseInt(userRole2));
            userRoles.add(userRoleResult2);
        }
        user.setUserRoles(userRoles);
        user.setId(Integer.parseInt(id));
        userService.update(user);
        System.out.println(user.toString());
        modelAndView.setViewName("redirect:/admin/showUsers");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.POST)
    public ModelAndView deleteUser(@PathVariable String id, ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/admin/showUsers");
        userService.remove(userService.getById(Integer.parseInt(id)));
        return modelAndView;
    }

    @RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(ModelAndView modelAndView, @PathVariable String id) {
        User user = userService.getById(Integer.parseInt(id));
        modelAndView.setViewName("delete-user");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.GET)
    public ModelAndView addPage(ModelAndView modelAndView) {
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("add-user");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute User user, @RequestParam(value = "roleAdmin", required = false) String userRole1,
                                @RequestParam(value = "roleUser", required = false) String userRole2,
                                ModelAndView modelAndView) {
        List<UserRole> userRoles = new ArrayList<>();
        if (userRole1 != null) {
            UserRole userRoleResult1 = userRoleService.getById(Integer.parseInt(userRole1));
            userRoles.add(userRoleResult1);
        }
        if (userRole2 != null) {
            UserRole userRoleResult2 = userRoleService.getById(Integer.parseInt(userRole2));
            userRoles.add(userRoleResult2);
        }
        user.setUserRoles(userRoles);
        userService.add(user);
        System.out.println(user.toString());
        modelAndView.setViewName("redirect:/admin/showUsers");
        return modelAndView;
    }

    @RequestMapping(value = "/user/forUsers/{id}", method = RequestMethod.GET)
    public ModelAndView forUsersPage(ModelAndView modelAndView, @PathVariable String id, Authentication authentication) {
        boolean isAdmin = false;
        boolean isUser = false;
        User principal = (User) authentication.getPrincipal();
        User user = userService.getById(Integer.parseInt(id));
        for (UserRole userRole : user.getAuthorities()) {
            if (userRole.getAuthority().equalsIgnoreCase("admin")) {
                isAdmin = true;
            }
            if (userRole.getAuthority().equalsIgnoreCase("user")) {
                isUser = true;
            }
        }
        modelAndView.setViewName("forUsers");
        modelAndView.addObject("userAuthorized", principal);
        modelAndView.addObject("isAdmin", isAdmin);
        modelAndView.addObject("isUser", isUser);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logoutPage(ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }
}