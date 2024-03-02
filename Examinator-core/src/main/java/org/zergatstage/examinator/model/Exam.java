package org.zergatstage.examinator.model;

import lombok.*;

import java.util.List;

/**
 * @author father
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
    private String title;
    @Singular
    private List<Section> sections;

}
