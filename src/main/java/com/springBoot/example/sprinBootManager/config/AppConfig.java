package com.springBoot.example.sprinBootManager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;

@EnableWebMvc
@Configuration
@ComponentScan
public class AppConfig implements WebMvcConfigurer {

    private final ServletContext servletContext;

    public AppConfig(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
//
//    @Bean(name ="templateResolver")
//    public ServletContextTemplateResolver getTemplateResolver() {
//        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
//        templateResolver.setPrefix("/resources/templates/");
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode("XHTML");
//        return templateResolver;
//    }
}
