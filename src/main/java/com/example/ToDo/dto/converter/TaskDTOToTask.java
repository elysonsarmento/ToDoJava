package com.example.ToDo.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.example.ToDo.dto.TaskDTO;
import com.example.ToDo.entity.Task;

@Component
public class TaskDTOToTask implements Converter<TaskDTO, Task> {

	private ModelMapper modelMapper;

    public TaskDTOToTask(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
	public Task convert(TaskDTO source) {
		return modelMapper.map(source, Task.class);
	}
}