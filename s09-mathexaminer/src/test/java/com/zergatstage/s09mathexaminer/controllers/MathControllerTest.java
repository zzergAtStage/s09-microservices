package com.zergatstage.s09mathexaminer.controllers;

import com.zergatstage.s09mathexaminer.model.Exams;
import com.zergatstage.s09mathexaminer.services.MathService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureWebTestClient
public class MathControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private MathService mathService;

    @BeforeEach
    public void setUp() {
        // Mocking behavior of MathService
        when(mathService.getRandomMathExercise()).thenReturn(
                Exams.builder().question("2 + 2 = ?").answer("4").build()
        );
    }

    @Test
    public void testGetFixedAmountQuestion() {
        // Send GET request to /api/questions/{amount}
        int amount = 3;
        webTestClient.get()
                .uri("/api/questions/{amount}", amount)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Exams.class)
                .hasSize(amount);
    }

    @Test
    public void testGetRandomQuestions() {
        // Send GET request to /api/questions/random?amount={amount}
        int amount = 2;
        webTestClient.get()
                .uri("/api/questions/random?amount={amount}", amount)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Exams.class)
                .hasSize(amount);
    }
}
