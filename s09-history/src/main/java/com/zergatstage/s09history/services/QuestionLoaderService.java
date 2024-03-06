package com.zergatstage.s09history.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zergatstage.s09history.model.ExamQuestion;
import com.zergatstage.s09history.repositories.HistoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author father
 */
@Service
public class QuestionLoaderService {

    @Autowired
    private HistoryRepository historyRepository;

    @PostConstruct
    public void loadQuestionsFromFile() throws IOException {
        String fileName = "default_history.json";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IOException("Could not find questions file: " + fileName);
        }

        ObjectMapper mapper = new ObjectMapper();
        List<ExamQuestion> questions = mapper.readValue(inputStream, new TypeReference<>() {
        });

        historyRepository.saveAll(questions); // Save all questions to the repository
    }
}
