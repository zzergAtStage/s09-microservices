package org.zergatstage.examinator.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.zergatstage.examinator.model.Exam;
import org.zergatstage.examinator.model.Exercise;
import org.zergatstage.examinator.model.Section;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author father
 */
@RestController
@RequestMapping("/exams")
public class ExamComposerController {
    private final RestTemplate restTemplate;
    private int number = 1;

    @Autowired
    public ExamComposerController(@LoadBalanced RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Operation(
            summary = "Generate questions for the exam",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Disciplines to generate questions for",
                    content = @Content(
                            schema = @Schema(implementation = Map.class),
                            examples = @ExampleObject(
                                    name = "Default request body",
                                    value = """
                                            {
                                            "math": 10,
                                            "history": 1
                                            }
                                            """
                            )
                    )
            )
    )
    @PostMapping("/exam")
    public Exam createExam(@RequestBody Map<String, Integer> examSpec) {
        List<Section> sections = examSpec.entrySet().stream()
                .map(this::getServiceUrl)
                .map(url -> restTemplate.getForObject(url, Exercise[].class))
                .map(Arrays::asList)
                .map(section -> Section.builder().exercises(section).build())
                .toList();
        return Exam.builder().sections(sections).title("Best exam #" + number++).build();
    }

    public String getServiceUrl(Map.Entry<String, Integer> entry) {
        return "http://" + entry.getKey() + "/api/questions/random?amount=" + entry.getValue();
    }
}
