package com.zerobase.communityproject.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  String projectName = "Community Project";

  @Bean
  public OpenAPI customOpenAPI() {
    Info info = new Info().title(projectName).version("1.0");

    Server localServer = new Server();
    localServer.setUrl("http://localhost:8080");
    localServer.setDescription("로컬서버");

    return new OpenAPI()
        .info(info)
        .servers(List.of(localServer))
        .addSecurityItem(new SecurityRequirement().addList(projectName))
        .components(new Components().addSecuritySchemes(projectName, securityScheme()));
  }

  private SecurityScheme securityScheme() {
    return new SecurityScheme()
        .type(SecurityScheme.Type.HTTP)
        .bearerFormat("JWT")
        .scheme("bearer")
        .in(SecurityScheme.In.HEADER);
  }

}
