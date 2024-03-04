package org.zergatstage.examinator.dao;

import org.springframework.stereotype.Repository;
import org.zergatstage.examinator.model.Exercise;

import java.util.List;

/**
 * @author father
 */
@Repository
public class ExerciseDaoImpl implements ExerciseDao {
    @Override
    public List<Exercise> getDefault() {
        return null;
    }
}
