package org.zergatstage.examinator.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zergatstage.examinator.model.CheckedExam;
import org.zergatstage.examinator.model.SolvedExam;
import org.zergatstage.examinator.services.ExamService;

/**
 * @author father
 */
@RestController
@RequestMapping("/exams")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @Operation(
            summary = "Check exam answers",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = SolvedExam.class),
                            examples = @ExampleObject(
                                    name = "Default solved exam",
                                    value = """
                                            {
                                              "student": "Random name",
                                              "exam": {
                                                "title": "Math",
                                                "sections": [
                                                  {
                                                    "title": "Basic calculations",
                                                    "desc": "Sum", 
                                                    "exercises": [
                                                      {
                                                        "question": "2+3", 
                                                        "answer": "5"
                                                      }
                                                    ]
                                                  }
                                                ]
                                              }
                                            }
                                            """
                            )
                    )
            )
    )
    @PostMapping("/check")
    public CheckedExam checkExam(@RequestBody SolvedExam solvedExam) {
        return examService.convert(solvedExam);
    }
}
