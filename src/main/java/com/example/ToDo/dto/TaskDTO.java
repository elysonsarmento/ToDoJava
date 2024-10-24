package com.example.ToDo.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}