package com.example.GestionSc.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

public class publicAPI {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-api")
                .packagesToScan("com.example.GestionSc.Web.") // Spécifiez le package contenant vos contrôleurs REST
                .build();
    }
}
