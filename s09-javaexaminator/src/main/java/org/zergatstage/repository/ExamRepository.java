package org.zergatstage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zergatstage.model.Exam;

/**
 * @author father
 */
public interface ExamRepository extends JpaRepository<Exam, Long> {
    Exam findBySessionId(String sessionId);
}