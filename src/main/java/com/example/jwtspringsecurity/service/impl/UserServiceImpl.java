package com.example.jwtspringsecurity.service.impl;

import com.example.jwtspringsecurity.model.entity.RoleEntity;
import com.example.jwtspringsecurity.model.entity.UserEntity;
import com.example.jwtspringsecurity.repository.RoleRepository;
import com.example.jwtspringsecurity.repository.UserRepository;
import com.example.jwtspringsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService , UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserEntity saveUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findUserEntityByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("User not found in the database");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new org.springframework.security.core.userdetails.User
                (user.getUsername(), user.getPassword(), authorities);
    }
}
