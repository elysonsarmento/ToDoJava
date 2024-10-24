package com.example.ToDo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.ToDo.dto.TaskDTO;
import com.example.ToDo.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletException;

@RestController
@RequestMapping("tasks")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasks(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam Optional<String> search) {

        String username = userDetails.getUsername();
        String searchTerm = search.orElse(""); // Usar uma string vazia se não houver busca

        logger.info("Fetching tasks for user: {} with search: {}", username, searchTerm);

        List<TaskDTO> tasks = taskService.findByUserAndTitle(username, searchTerm);

        if (tasks.isEmpty()) {
            logger.info("No tasks found for user: {} with search: {}", username, searchTerm);
            return ResponseEntity.noContent().build(); // Retorna 204 se não houver tarefas
        }

        return ResponseEntity.ok(tasks);
    }


    @PostMapping
    public ResponseEntity<TaskDTO> post(@RequestBody TaskDTO taskDTO) {
        logger.info("Creating new task: {}", taskDTO);
        TaskDTO savedTaskDTO = taskService.add(taskDTO);
        return new ResponseEntity<>(savedTaskDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable int id) {
        logger.info("Deleting task with id: {}", id);
        taskService.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable int id, @RequestBody TaskDTO taskDTO) {
        logger.info("Updating task with id: {}", id);
        taskDTO.setId(id);
        return ResponseEntity.ok(taskService.update(taskDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskDTO> getOne(@PathVariable int id) {
        logger.info("Fetching task with id: {}", id);
        return ResponseEntity.ok(taskService.findById(id));
    }

    @ExceptionHandler(ServletException.class)
    public ResponseEntity<String> handleJwtException(ServletException e) {
        logger.error("JWT error: {}", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        logger.error("An error occurred: ", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
