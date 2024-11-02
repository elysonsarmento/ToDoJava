package com.example.ToDo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_entity")
public class UserEntity {

    @Id
    @UuidGenerator()
    private UUID id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "user")
    private Set<Task> tasks = new HashSet<>();

}