package com.example.ToDo.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.example.ToDo.dto.UserDTO;
import com.example.ToDo.entity.UserEntity;


@Component
public class UserDTOToUser implements Converter<UserDTO, UserEntity> {
	private final ModelMapper modelMapper;

	public UserDTOToUser(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public UserEntity convert(@NonNull UserDTO source) {
		// TODO Auto-generated method stub
		return modelMapper.map(source, UserEntity.class);
	}

}
