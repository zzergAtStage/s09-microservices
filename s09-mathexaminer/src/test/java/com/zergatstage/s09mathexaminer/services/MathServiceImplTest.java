package com.zergatstage.s09mathexaminer.services;

import com.zergatstage.s09mathexaminer.model.Exams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MathServiceImplTest {
    @Mock
    private Random random;

    @Value("${math.max}")
    private int max;

    @InjectMocks
    private MathServiceImpl mathService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    @DisplayName("Test RandomExercise method")
    void getRandomMathExercise() {
        // Mocking behavior of Random
        when(random.nextInt(max)).thenReturn(3).thenReturn(4); // Mocking random numbers
        // Calling the method being tested
        Exams exam = mathService.getRandomMathExercise();
        // Verifying the result
        assertEquals("3 + 4 = ?", exam.getQuestion());
        assertEquals("7", exam.getAnswer());
    }

    @Test
    void getMaxRandomRange() {
        // Calling the method being tested
        int maxRange = mathService.getMaxRandomRange();
        // Verifying the result
        assertEquals(max, maxRange);
    }
}