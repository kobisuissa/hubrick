package com.hubrick.challenge.v1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by kobi on 11/03/16.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket usersV1Api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("hubrick-challenge")
                .apiInfo(apiV1Info())
                .select()
                .paths(regex("/users/v1/*"))
                .build();
    }

    private ApiInfo apiV1Info() {
        return new ApiInfoBuilder()
                .title("Hubrick Challenge - User Management System V1.0")
                .description("This API allows to perform all necessary operations to manage users in the Hubrick Challenge User Management System.")
                .version("1.0")
                .build();
    }

}
