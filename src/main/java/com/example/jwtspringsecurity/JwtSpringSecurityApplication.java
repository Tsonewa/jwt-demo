package com.example.jwtspringsecurity;

import com.example.jwtspringsecurity.model.entity.RoleEntity;
import com.example.jwtspringsecurity.model.entity.UserEntity;
import com.example.jwtspringsecurity.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JwtSpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtSpringSecurityApplication.class, args);
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
            userService.saveRole(new RoleEntity("ROLE_USER"));
            userService.saveRole(new RoleEntity("ROLE_MANAGER"));
            userService.saveRole(new RoleEntity("ROLE_ADMIN"));
            userService.saveRole(new RoleEntity("ROLE_SUPER_ADMIN"));

            userService.saveUser(new UserEntity("John Doe", "john", "1234", new ArrayList<>()));
            userService.saveUser(new UserEntity("Max Mark", "max", "1234", new ArrayList<>()));
            userService.saveUser(new UserEntity("Tom Musk", "tom", "1234", new ArrayList<>()));
            userService.saveUser(new UserEntity("Elon Jo", "jerry", "1234", new ArrayList<>()));

            userService.addUserAuthorization("john", "ROLE_USER");
            userService.addUserAuthorization("john", "ROLE_ADMIN");
            userService.addUserAuthorization("john", "ROLE_MANAGER");
            userService.addUserAuthorization("john", "ROLE_SUPER_ADMIN");
            userService.addUserAuthorization("max", "ROLE_MANAGER");
            userService.addUserAuthorization("tom", "ROLE_USER");
            userService.addUserAuthorization("jerry", "ROLE_USER");
        };
    }

}
