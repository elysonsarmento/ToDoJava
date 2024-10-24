package com.example.ToDo.dto;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import com.example.ToDo.entity.Task;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public @NotEmpty(message = "O email não pode estar vazio") @Email String getEmail() {
		return email;
	}

	public void setEmail(@NotEmpty(message = "O email não pode estar vazio") @Email String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public @Length(max = 15) String getLastName() {
		return lastName;
	}

	public void setLastName(@Length(max = 15) String lastName) {
		this.lastName = lastName;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
}