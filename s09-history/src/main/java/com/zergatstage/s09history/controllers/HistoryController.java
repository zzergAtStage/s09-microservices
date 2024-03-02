package com.zergatstage.s09history.controllers;

import com.zergatstage.s09history.model.ExamQuestion;
import com.zergatstage.s09history.services.HistoryServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author father
 */
@RestController
@RequestMapping("/api")
public class HistoryController {
    private final HistoryServiceImpl historyService;

    public HistoryController(HistoryServiceImpl historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/questions/{amount}")
    public List<ExamQuestion> getSomeQuestions(@RequestParam(value = "amount", defaultValue = "1") int amount){
        return historyService.getRandom(amount);
    }
}
