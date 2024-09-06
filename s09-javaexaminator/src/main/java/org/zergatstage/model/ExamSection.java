package org.zergatstage.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * @author father
 */
@Entity
@Data
public class ExamSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sectionName; // Could be "Topic1", "Easy", "Hard", etc.

    @ManyToOne
    private Exam exam; // Reference to the parent exam

    @OneToMany(mappedBy = "section")
    private List<UserAnswer> userAnswers;

}
