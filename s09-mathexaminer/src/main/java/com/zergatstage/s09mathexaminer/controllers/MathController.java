package com.zergatstage.s09mathexaminer.controllers;

import com.zergatstage.s09mathexaminer.model.Exams;
import org.springframework.web.bind.annotation.*;
import com.zergatstage.s09mathexaminer.services.MathService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author father
 */
@RestController
@RequestMapping("/api")
public class MathController {
    private MathService mathService;
    public MathController(MathService service){
        this.mathService = service;
    }
    @GetMapping("/questions/{amount}")
    public List<Exams> getFixedAmountQuestion(@PathVariable("amount") int amount) {
        List<Exams> questions = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            questions.add(mathService.getRandomMathExercise());
        }
        return questions;
    }
    @GetMapping("/questions/random")
    public List<Exams> getRandomQuestions(@RequestParam(value = "amount",defaultValue = "1") Integer amount){
        return Stream.generate(mathService::getRandomMathExercise).limit(amount).toList();
    }
}
