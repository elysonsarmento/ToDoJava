package com.example.ToDo.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;

@Data
public class TaskDTO {

    @JsonProperty(access = Access.READ_ONLY, value = "id")
    private int id;

    private String title;

    private String description;

    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime creationDateTime;

    private boolean finished;

    // Public no-argument constructor
    public TaskDTO() {
    }

    public TaskDTO(int id, String title, String description, LocalDateTime creationDateTime, boolean finished) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creationDateTime = creationDateTime;
        this.finished = finished;
    }

}