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
public class Section {
    private String title;
    private String description;
    @Singular
    private List<Exercise> exercises;
}
