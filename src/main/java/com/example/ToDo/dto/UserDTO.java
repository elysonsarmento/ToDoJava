package com.example.ToDo.dto;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import com.example.ToDo.entity.Task;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Data
public class UserDTO {

	@JsonProperty(access = Access.READ_ONLY, required = false)
	private String id;

	@NotEmpty(message = "O email não pode estar vazio")
	@Email
	private String email;

	private String password;

	private String firstName;

	@Length(max = 15)
	private String lastName;

	private Set<Task> tasks = new HashSet<>();



	public @NotEmpty(message = "O email não pode estar vazio") @Email String getEmail() {
		return email;
	}

	public void setEmail(@NotEmpty(message = "O email não pode estar vazio") @Email String email) {
		this.email = email;
	}

}