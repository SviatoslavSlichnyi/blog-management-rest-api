package slichnyi.sviatoslav.blog.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Blog Management API", version = "v1"),
        security = @SecurityRequirement(name = "JWT"),
        servers = @Server(url = "/", description = "Default Server URL")
)
@SecurityScheme(
        type = SecuritySchemeType.APIKEY,
        name = "JWT",
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
}
