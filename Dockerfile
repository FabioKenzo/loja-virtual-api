# Usando a imagem da Oracle que já possui o JDK 25 disponível
FROM container-registry.oracle.com/java/openjdk:25

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia o seu JAR 
COPY target/loja-virtual-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta do Spring
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]