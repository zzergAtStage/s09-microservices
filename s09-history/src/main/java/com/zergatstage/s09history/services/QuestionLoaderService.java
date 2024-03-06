package com.zergatstage.s09history.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zergatstage.s09history.model.ExamQuestion;
import com.zergatstage.s09history.repositories.HistoryRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

/** Loads pre initialized  questions
 * @author father
 */
@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class QuestionLoaderService {
    @Value("${questions.file}")
    private String fileName;
    @Autowired
    private HistoryRepository historyRepository;

    @SneakyThrows
    @PostConstruct //call this method after all beans are initialized
    public void loadQuestionsFromFile(){



        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

        if (inputStream == null) {
            Logger.getAnonymousLogger().warning("Could not find questions file.");
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        List<ExamQuestion> questions = mapper.readValue(inputStream, new TypeReference<>() {
        });

        historyRepository.saveAll(questions); // Save all questions to the repository
    }
}
