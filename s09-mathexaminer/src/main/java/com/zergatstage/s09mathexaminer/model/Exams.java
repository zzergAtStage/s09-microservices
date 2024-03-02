package com.zergatstage.s09mathexaminer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** A model, that represents a simple exam with generated question and answer
 * @author zergatstage
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exams {
    private String question;
    private String answer;

}
