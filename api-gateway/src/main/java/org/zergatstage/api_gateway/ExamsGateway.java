package org.zergatstage.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GatewayFilter;
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
                .route("MATH", r -> r.path("/MATH/**")
                        .filters(f -> f.filter(mathServiceFilter())
                                .rewritePath("/MATH/(?<remaining>.*)", "/$\\{remaining}"))
                        .uri("lb://MATH"))
                .route("HISTORY", r -> r.path("/HISTORY/**")
                        .filters(f -> f.filter(historyServiceFilter())
                                .rewritePath("/HISTORY/(?<remaining>.*)", "/$\\{remaining}"))
                        .uri("lb://HISTORY"))
                .build();
    }

    @Bean
    public GatewayFilter mathServiceFilter() {
        return (exchange, chain) -> chain.filter(exchange.mutate()
                .request(exchange.getRequest().mutate()
                        .path("/api/questions/" + extractAmount(exchange.getRequest().getPath().toString()))
                        .build()).build());
    }

    @Bean
    public GatewayFilter historyServiceFilter() {
        return (exchange, chain) -> chain.filter(exchange.mutate()
                .request(exchange.getRequest().mutate()
                        .path("/api/questions/" + extractAmount(exchange.getRequest().getPath().toString()))
                        .build()).build());
    }

    private String extractAmount(String path) {
        // Extracts the amount from the request path, assuming it's the last part of the path
        String[] parts = path.split("/");
        return parts[parts.length - 1];
    }
}
