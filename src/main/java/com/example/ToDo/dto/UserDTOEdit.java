package com.example.ToDo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.validation.constraints.AssertTrue;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword2() {
		return newPassword2;
	}

	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}
}