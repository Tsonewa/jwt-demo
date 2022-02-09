package com.example.jwtspringsecurity.service;

import com.example.jwtspringsecurity.model.entity.RoleEntity;
import com.example.jwtspringsecurity.model.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity saveUser(UserEntity user);
    RoleEntity saveRole(RoleEntity role);
    void addUserAuthorization(String name, String roleName);
    UserEntity getUser(String username);
    List<UserEntity> getAllUsers();
}
