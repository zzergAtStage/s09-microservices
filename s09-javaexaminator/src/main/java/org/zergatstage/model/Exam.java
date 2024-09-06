package org.zergatstage.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author father
 */
@Entity
@Data
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sessionId; // Unique ID for this exam session
    private LocalDateTime examDate;

    @ManyToOne
    private User user; // The user taking the exam

    @OneToMany(mappedBy = "exam")
    private List<ExamSection> sections; // Sections of the exam (grouped by topic/difficulty)

}
