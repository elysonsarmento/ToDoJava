package com.example.ToDo.service;

import java.util.List;

import com.example.ToDo.entity.Task;
import org.springframework.http.HttpStatus;

import com.example.ToDo.dto.TaskDTO;

public interface TaskService extends CrudService<TaskDTO,Integer> {

    public List<TaskDTO> findByUser_Email(String email);

    public List<TaskDTO> findByUserAndTitle(String email, String title);
	
	


	

}
