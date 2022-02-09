package com.example.jwtspringsecurity.api;

import com.example.jwtspringsecurity.model.entity.RoleEntity;
import com.example.jwtspringsecurity.model.entity.UserEntity;
import com.example.jwtspringsecurity.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<RoleEntity> saveUser(@RequestBody RoleEntity role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addRole")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addUserAuthorization(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }
}

@Getter
@Setter
class RoleToUserForm {
    private String username;
    private String roleName;
}
