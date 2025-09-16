package com.gtalent.commerce.service.configs;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@OpenAPIDefinition
@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("E-Commerce Admin Dashboard APIs")
                        .description("根據 https://marmelab.com/react-admin-demo ，使用Springboot + MySQL 開發管理後台API系統，並以Swagger 呈現API文件。")
                        .version("v1.0.0")

                )
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                ).addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder().group("public-apis")
                .pathsToMatch("/v1/users/**", "/v1/segments/**", "/v1/products/**", "/v1/categories/**", "/v1/reviews/**", "/v1/orders/**", "/jwt/**")
                .build();
    }
}