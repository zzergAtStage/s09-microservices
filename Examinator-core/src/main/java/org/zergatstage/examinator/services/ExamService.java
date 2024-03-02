package org.zergatstage.examinator.services;

import org.zergatstage.examinator.model.CheckedExam;
import org.zergatstage.examinator.model.Exercise;
import org.zergatstage.examinator.model.SolvedExam;

import java.util.List;

/**
 * @author father
 */
public interface ExamService {

    CheckedExam convert(SolvedExam solvedExam);

    List<Exercise> getExercises();
}
