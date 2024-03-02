package com.zergatstage.s09history.repositories;

import com.zergatstage.s09history.model.ExamQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author father
 */
public interface HistoryRepository extends JpaRepository<ExamQuestion, Integer> {
}
