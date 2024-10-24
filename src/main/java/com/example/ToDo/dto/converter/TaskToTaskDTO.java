package com.example.ToDo.dto.converter;

import java.util.ArrayList;
import java.util.List;

import com.example.ToDo.dto.TaskDTO;
import com.example.ToDo.entity.Task;
import io.micrometer.common.lang.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TaskToTaskDTO implements Converter<Task, TaskDTO> {

    private final ModelMapper modelMapper;

    @Autowired
    public TaskToTaskDTO(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TaskDTO convert(@NonNull Task source) {
        return modelMapper.map(source, TaskDTO.class);
    }

    public List<TaskDTO> convert(List<Task> tasks) {
        List<TaskDTO> tasksDTO = new ArrayList<>();
        for (Task task : tasks) {
            tasksDTO.add(convert(task));
        }
        return tasksDTO;
    }
}