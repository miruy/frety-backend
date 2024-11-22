package me.frety.frety_back.api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FRETY API")
                        .description("")
                        .version("1.0.0"))
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new io.swagger.v3.oas.models.security.SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .in(SecurityScheme.In.HEADER)
                                        .bearerFormat("JWT")));
    }
}
