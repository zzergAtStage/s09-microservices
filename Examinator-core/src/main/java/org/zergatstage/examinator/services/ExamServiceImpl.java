package org.zergatstage.examinator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.zergatstage.examinator.dao.ExerciseDao;
import org.zergatstage.examinator.model.CheckedExam;
import org.zergatstage.examinator.model.Exercise;
import org.zergatstage.examinator.model.SolvedExam;

import java.util.List;

/**
 * @author father
 */
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExerciseDao exerciseDao;

    @Autowired
    private SectionDao sectionDao;

    @Override
    public CheckedExam convert(SolvedExam solvedExam) {
        int STATIC_GRADE = 80;
        return CheckedExam.builder().exam(solvedExam.getExam()).mark(STATIC_GRADE).build();
    }

    @Override
    public List<Exercise> getExercises() {
        return exerciseDao.getDefault();
    }
}
