package com.example.ToDo.dto;


import lombok.Data;

@Data
public class TokenDTO {

	private String token;

	public TokenDTO(String token) {
		this.token = token;
	}

}