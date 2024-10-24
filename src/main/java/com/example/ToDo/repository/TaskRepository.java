package com.example.ToDo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ToDo.entity.Task;


public interface TaskRepository extends JpaRepository<Task, Integer>{
	List<Task> findByUser_Email(String email);

	List<Task> findByUser_EmailAndTitleContainingIgnoreCase(String email, String title);

}
