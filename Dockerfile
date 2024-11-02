# Use a imagem do Maven para compilar o projeto
FROM maven:3.9.9-eclipse-temurin-23 AS builder
WORKDIR /app

# Copie o arquivo pom.xml e baixe as dependências
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copie o código-fonte e compile o projeto
COPY src ./src
RUN mvn package -DskipTests

# Use uma imagem menor do OpenJDK para rodar a aplicação
FROM openjdk:23-jdk-slim
WORKDIR /app

# Copie o JAR gerado pelo estágio de build
COPY --from=builder /app/target/*.jar app.jar

# Exponha a porta 8080
EXPOSE 8080

# Defina o comando de entrada para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]