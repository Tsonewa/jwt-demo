package com.example.jwtspringsecurity.service.impl;

import com.example.jwtspringsecurity.model.entity.RoleEntity;
import com.example.jwtspringsecurity.model.entity.UserEntity;
import com.example.jwtspringsecurity.repository.RoleRepository;
import com.example.jwtspringsecurity.repository.UserRepository;
import com.example.jwtspringsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Override
    public UserEntity saveUser(UserEntity user) {
        return this.userRepository.save(user);
    }

    @Override
    public RoleEntity saveRole(RoleEntity role) {
        return this.roleRepository.save(role);
    }

    @Override
    public void addUserAuthorization(String name, String roleName) {

        UserEntity user = this.userRepository.findUserEntityByUsername(name);
        RoleEntity role = this.roleRepository.findRoleEntityByName(roleName);

        user.getRoles().add(role);
    }

    @Override
    public UserEntity getUser(String username) {
        return userRepository.findUserEntityByUsername(username);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return this.userRepository.findAll();
    }
}
