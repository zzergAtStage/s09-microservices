package com.zergatstage.s09history.services;

import com.zergatstage.s09history.model.ExamQuestion;
import com.zergatstage.s09history.repositories.HistoryRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author father
 */
@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    @Setter
    private HistoryRepository repository;

    @Setter
    @Getter
    private int version;

    @Override
    public List<ExamQuestion> getRandom(int random) {
        List<ExamQuestion> questionsAll = repository.findAll();
        Collections.shuffle(questionsAll);
        return questionsAll.subList(0, random);
    }

    @Override
    @EventListener(ContextRefreshedEvent.class) //
    public void fillDB() {
        Logger.getAnonymousLogger().info("Fill DB with some data");
        List<ExamQuestion> exercises = Arrays.asList(
                ExamQuestion.builder().question("When WW2 was started?").answer("1939").build(),
                ExamQuestion.builder().question("Tell the date of birthe of Winston Churchill?").answer("Far far away...").build()
        );
        repository.saveAll(exercises);

    }

    @Override
    public void setVersion(int version) {

    }

    @Override
    public int getVersion() {
        return 0;
    }
}
