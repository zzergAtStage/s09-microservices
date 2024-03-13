package org.zergatstage.examinator;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import org.zergatstage.examinator.services.IntegrationFileGateway;

@SpringBootApplication
@EnableScheduling

public class ExaminatorApplication {

    @Value("${exam.title}")
    private String title;

    @Scheduled(fixedDelay = 100000)
    public void printTitle() {
        System.out.println("title = " + title);
    }



    @Bean
    @Primary
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplateWithZul(RestTemplateBuilder builder) {
        return builder.build();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ExaminatorApplication.class);
        System.out.println();
    }
}