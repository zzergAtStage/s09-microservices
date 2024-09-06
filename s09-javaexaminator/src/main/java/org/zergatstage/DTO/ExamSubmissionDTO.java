package org.zergatstage.DTO;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author father
 */
@Data
public class ExamSubmissionDTO {
    private String sessionId;
    private Long userId;
    private Map<String, List<UserAnswerDTO>> sectionAnswers; // Section name as key, list of answers as value
}
