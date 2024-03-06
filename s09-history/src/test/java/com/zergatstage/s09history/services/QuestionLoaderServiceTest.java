package com.zergatstage.s09history.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zergatstage.s09history.model.ExamQuestion;
import com.zergatstage.s09history.repositories.HistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;

import java.io.InputStream;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuestionLoaderServiceTest {

    private QuestionLoaderService questionLoaderService;

    @Mock
    private HistoryRepository historyRepository;
    //testing with the same questions.
    private static final String FILE_NAME = "test_history.json";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        questionLoaderService = new QuestionLoaderService(FILE_NAME, historyRepository);
    }

    @Test
    public void testLoadQuestionsFromFile() throws Exception {
        // Mocking the input stream from the file
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
        ObjectMapper objectMapper = new ObjectMapper();
        List<ExamQuestion> questions = objectMapper.readValue(inputStream, new TypeReference<>() {
        });

        // Mocking behavior of historyRepository.saveAll
        when(historyRepository.saveAll(any())).thenReturn(questions);

        // Calling the method being tested
        questionLoaderService.loadQuestionsFromFile();

        // Verifying that saveAll method was called with correct arguments
        verify(historyRepository).saveAll(questions);
    }
}
