package com.example.jwtspringsecurity.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class RoleEntity extends BaseEntity{

    private String name;

}
