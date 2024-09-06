package org.zergatstage.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author father
 */
@Entity
@Data
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ExamSection section;

    @ManyToOne
    private JavaQuizQuestion question;

    private String userAnswer; // The answer submitted by the user
    private boolean correct; // If the answer was correct
    private int pointsAwarded; // Points for the question

}
