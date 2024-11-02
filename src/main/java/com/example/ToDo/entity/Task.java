package com.example.ToDo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;

	private String description;

	private LocalDateTime creationDateTime;

	private boolean finished;

	@ManyToOne
	private UserEntity user;

}