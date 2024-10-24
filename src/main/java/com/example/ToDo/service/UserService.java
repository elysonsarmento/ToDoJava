package com.example.ToDo.service;


import com.example.ToDo.dto.UserDTO;
import com.example.ToDo.dto.UserDTOEdit;

public interface UserService extends CrudService<UserDTO,String>  {
	
	UserDTO findByEmail(String email);

	public UserDTO edit(UserDTOEdit userDTOEdit);

}
