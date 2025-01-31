package com.coderhouse.tienda_ch.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST Full | Java 62940 | CoderHouse")
                        .version("2.0.0")
                        .description("La API REST proporciona endpoints para administrar una pizzeria "
                                + "Permite realizar operaciones "
                                + "CRUD (Crear, Leer, Actualizar, Eliminar) usuarios , productos, categorias "
                                + "Los endpoints permiten listar, agregar, mostrar, "
                                + "editar y eliminar usuarios y productos. La API está documentada utilizando "
                                + "Swagger, lo que facilita la comprensión de los endpoints y su uso.")
                        .contact(new Contact()
                                .name("Florencia Urrejola Ferrer")
                                .email("florurre@gmail.com")
                                .url("https://www.linkedin.com/in/florenciauferrer/"))
                )
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor Local")
                ));
    }


}
