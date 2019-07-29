package com.stackroute.MuzixCrudOperations.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

//http://ws.audioscrobbler.com/2.0/?method=geo.gettoptracks&country=spain&api_key=0a6047c7b2d86bd8ac09efb4682410ba&format=json

@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.stackroute.MuzixCrudOperations.controller"))
                .paths(regex("/api/v1.*"))
                .build();
    }

}
