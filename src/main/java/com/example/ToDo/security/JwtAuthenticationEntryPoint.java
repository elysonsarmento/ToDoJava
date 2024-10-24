package com.example.ToDo.security;

import java.io.IOException;
import java.io.Serializable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.security.web.AuthenticationEntryPoint;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
						 AuthenticationException authException) throws IOException {

		// Configura o código de erro 401 Unauthorized
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		// Define o tipo de conteúdo da resposta como JSON
		response.setContentType("application/json");

		// Cria um mapa para armazenar a mensagem de erro personalizada
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("status", HttpServletResponse.SC_UNAUTHORIZED);
		errorDetails.put("error", "Unauthorized");
		errorDetails.put("message", "Acesso negado. Você deve fornecer um token válido para acessar este recurso.");
		errorDetails.put("path", request.getServletPath());

		// Converte o mapa em JSON e envia a resposta
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), errorDetails);
	}
}
