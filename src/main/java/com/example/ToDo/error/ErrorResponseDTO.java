package com.example.ToDo.error;

import java.time.LocalDateTime;

public class ErrorResponseDTO {
    private String message;
    private int status;
    private LocalDateTime timestamp;

    // Construtor privado para forçar o uso do builder
    private ErrorResponseDTO(Builder builder) {
        this.message = builder.message;
        this.status = builder.status;
        this.timestamp = builder.timestamp;
    }

    // Getters e Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // Método estático para iniciar o builder
    public static Builder builder() {
        return new Builder();
    }

    // Classe Builder interna
    public static class Builder {
        private String message;
        private int status;
        private LocalDateTime timestamp;

        // Métodos do builder para definir os valores
        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        // Método para construir a instância de ErrorResponseDTO
        public ErrorResponseDTO build() {
            return new ErrorResponseDTO(this);
        }
    }
}
