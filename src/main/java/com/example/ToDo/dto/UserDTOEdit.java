package com.example.ToDo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.validation.constraints.AssertTrue;
import lombok.Data;


@Data
public class UserDTOEdit {

	@JsonProperty(access = Access.READ_ONLY)
	private int id;

	private String email;

	private String firstName;

	private String lastName;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String oldPassword;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String newPassword;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String newPassword2;

	public UserDTOEdit(int id, String email, String firstName, String lastName, String oldPassword, String newPassword, String newPassword2) {
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.newPassword2 = newPassword2;
	}

	@AssertTrue
	private boolean arePasswordsEqual() {
		return newPassword.equals(newPassword2);
	}

}