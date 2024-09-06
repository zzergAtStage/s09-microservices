package org.zergatstage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zergatstage.model.JavaQuizQuestion;

import java.util.List;

/**
 * @author father
 */
public interface JavaQuizRepository extends JpaRepository<JavaQuizQuestion, Long> {
    // Fetch questions by difficulty level
    List<JavaQuizQuestion> findByDifficultyLevel(int level);
}
