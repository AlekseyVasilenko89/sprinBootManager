package com.springBoot.example.sprinBootManager.config;

import com.springBoot.example.sprinBootManager.model.User;
import com.springBoot.example.sprinBootManager.model.UserRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws
            IOException {
        User principal = (User) authentication.getPrincipal();
        boolean isAdmin = false;
        boolean isUser = false;
        boolean isAnonymous = false;
        for (UserRole userRole : principal.getAuthorities()) {
            if (userRole.getAuthority().equalsIgnoreCase("admin")) {
                isAdmin = true;
            }
            if (userRole.getAuthority().equalsIgnoreCase("user")) {
                isUser = true;
            }
        }

        if (isUser) {
            response.sendRedirect("/user/forUsers");
        } else if (isAdmin) {
            response.sendRedirect("/admin/showUsers");
        } else if (isAnonymous) {
            response.sendRedirect("/login");
        } else {
            throw new IllegalStateException();
        }
    }
}
