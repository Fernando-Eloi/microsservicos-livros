package br.com.fernando.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;

@OpenAPIDefinition(info =
@Info(title = "Exchange Microservice API",
        version = "v1",
        description = "Documentation of"
))
public class OpenApiConfiguration {


    public OpenAPI cusOpenAPI(){
        return new OpenAPI()
                .components(new Components())
                .info( new io.swagger.v3.oas.models.info.Info()
                        .title("Exchange Microservice API")
                        .version("v1")
                        .license(
                                new License()
                                        .name("Apache 2.0")
                                        .url("https://github.com/Fernando-Eloi/microsservices-with-spring-cloud-java-erudio")
                        )
                );
    }
}
