package org.zergatstage.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExamsGateway {

    public static void main(String[] args) {
        SpringApplication.run(ExamsGateway.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("MATH",r->r.path("/MATH/**")
                        .uri("http://localhost:8083/"))
                .route("HISTORY",r->r.path("/HISTORY/**")
                        .uri("http://localhost:8085/")).build();}
}