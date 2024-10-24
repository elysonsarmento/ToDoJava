package com.example.ToDo.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.ToDo.dto.UserDTO;
import com.example.ToDo.entity.UserEntity;

@Component
public class UserToUserDTO implements Converter<UserEntity, UserDTO> {

	private final ModelMapper modelMapper;

	public UserToUserDTO(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public UserDTO convert(UserEntity source) {


        return modelMapper.map(source, UserDTO.class);
	}
}
