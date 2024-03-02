package com.zergatstage.s09mathexaminer.services;

import com.zergatstage.s09mathexaminer.model.Exams;

/**
 * @author zergatstage
 */
public interface MathService {
    /**
     * Generating a simple exam question
     * @return Exam entity
     */
    Exams getRandomMathExercise();

    /**
     * returns a max range of question in math
     * @return
     */
    int getMaxRandomRange();

}
