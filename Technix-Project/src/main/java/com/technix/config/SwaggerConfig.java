package com.technix.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Technix India API")
                        .version("1.0")
                        .description("This is a Technix API built using Spring Boot and Swagger"))
                .components(new Components()
                        .addParameters("Content-Type",
                                new Parameter()
                                        .in("header")
                                        .schema(new StringSchema())
                                        .name("Content-Type")
                                        .required(true)
                                        .example("multipart/form-data")))
                // Add JWT Security Requirement
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(
                        new io.swagger.v3.oas.models.Components()
                                .addSecuritySchemes("Bearer Authentication",
                                        new SecurityScheme()
                                                .type(Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                                .in(In.HEADER)
                                                .name("Authorization")));
    }
}
