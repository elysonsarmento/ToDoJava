package com.example.ToDo.service.implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.ToDo.dto.TaskDTO;
import com.example.ToDo.dto.converter.TaskDTOToTask;
import com.example.ToDo.dto.converter.TaskToTaskDTO;
import com.example.ToDo.entity.Task;
import com.example.ToDo.entity.UserEntity;
import com.example.ToDo.repository.TaskRepository;
import com.example.ToDo.repository.UserRepository;
import com.example.ToDo.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

    final TaskRepository taskRepository;

    final TaskToTaskDTO taskToTaskDTO;

    final TaskDTOToTask taskDTOToTask;

    final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskToTaskDTO taskToTaskDTO, TaskDTOToTask taskDTOToTask, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.taskToTaskDTO = taskToTaskDTO;
        this.taskDTOToTask = taskDTOToTask;
        this.userRepository = userRepository;
    }

    @Override
    public List<TaskDTO> findByUser_Email(String email) {
        List<Task> tasks = taskRepository.findByUser_Email(email);
        return taskToTaskDTO.convert(tasks);
    }

    @Override
    public List<TaskDTO> findByUserAndTitle(String email, String title) {
        return taskToTaskDTO.convert(taskRepository.findByUser_EmailAndTitleContainingIgnoreCase(email, title));
    }

    @Override
    public TaskDTO add(TaskDTO taskDTO) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Task task = taskDTOToTask.convert(taskDTO);
        UserEntity user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        assert task != null;
        task.setUser(user);
        task.setCreationDateTime(LocalDateTime.now());
        return taskToTaskDTO.convert(taskRepository.save(task));
    }

    @Override
    public TaskDTO findById(Integer id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        return taskToTaskDTO.convert(task);
    }

    @Override
    public List<TaskDTO> findAll() {
        return taskToTaskDTO.convert(taskRepository.findAll());
    }

    @Override
    public void deleteById(Integer id) {
        taskRepository.deleteById(id);
    }

    @Override
    public TaskDTO update(TaskDTO taskDTO) {
        Task task = taskRepository.findById(taskDTO.getId()).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setFinished(taskDTO.isFinished());
        task.setDescription(taskDTO.getDescription());
        task.setTitle(taskDTO.getTitle());
        return taskToTaskDTO.convert(taskRepository.save(task));
    }
}