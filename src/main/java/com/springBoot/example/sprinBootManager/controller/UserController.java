package com.springBoot.example.sprinBootManager.controller;

import com.springBoot.example.sprinBootManager.model.User;
import com.springBoot.example.sprinBootManager.model.UserRole;
import com.springBoot.example.sprinBootManager.service.UserRoleServiceImpl;
import com.springBoot.example.sprinBootManager.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/")
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
    public String allUser(ModelAndView modelAndView) {
        modelAndView.addObject("users", userService.getAll());
        modelAndView.setViewName("index");
        return "index";
    }

    @RequestMapping(value = "/admin/update/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(ModelAndView modelAndView, @PathVariable String id) {
        User user = userService.getById(Integer.parseInt(id));
        List<UserRole> userRoles = user.getAuthorities();
        modelAndView.setViewName("updatePage");
        modelAndView.addObject("user", user);
        modelAndView.addObject("userRoles", userRoles);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute User user, @RequestParam(value = "roleAdmin", required = false) String userRole1,
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
        userService.update(user);
        System.out.println(user.toString());
        modelAndView.setViewName("redirect:/admin/showUsers");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(ModelAndView modelAndView, @PathVariable String id) {
        User user = userService.getById(Integer.parseInt(id));
        modelAndView.setViewName("deletePage");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
    public ModelAndView deleteUser(@ModelAttribute("user") User user, ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/admin/showUsers");
        userService.remove(user);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.GET)
    public ModelAndView addPage(ModelAndView modelAndView) {
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

    @RequestMapping(value = "/user/forUsers", method = RequestMethod.GET)
    public ModelAndView forUsersPage(ModelAndView modelAndView) {
        modelAndView.setViewName("forUsers");
        return modelAndView;
    }

    @RequestMapping(value = "lotout", method = RequestMethod.GET)
    public ModelAndView logoutPage(ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }
}