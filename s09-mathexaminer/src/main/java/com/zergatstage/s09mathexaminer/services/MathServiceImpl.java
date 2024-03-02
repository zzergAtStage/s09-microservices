package com.zergatstage.s09mathexaminer.services;

import com.zergatstage.s09mathexaminer.model.Exams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author father
 */
@Service
public class MathServiceImpl implements MathService {
    private Random random = new Random();
        @Value("${math.max}")
        private int max;
    /**
     * Generating a simple exam question
     *
     * @return Exam entity
     */
    @Override
    public Exams getRandomMathExercise() {
        int a = random.nextInt(getMaxRandomRange());
        int b = random.nextInt(getMaxRandomRange());
        return Exams.builder().question(a + " + " + b + " = ?").answer(Integer.toString(a + b)).build();
    }

    /**
     * returns a max range of question in math
     *
     * @return
     */
    @Override
    public int getMaxRandomRange() {
        return max;
    }
}
