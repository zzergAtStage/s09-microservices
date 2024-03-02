package org.zergatstage.examinator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

/**
 * @author father
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolvedExam {
    private Integer mark;
    @Delegate
    @JsonIgnore
    private Exam exam = new Exam();
}
