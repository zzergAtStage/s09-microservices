package org.zergatstage.examinator.controllers;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.zergatstage.examinator.model.Exam;
import org.zergatstage.examinator.model.Exercise;
import org.zergatstage.examinator.model.Section;
import org.zergatstage.examinator.services.IntegrationFileGateway;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * @author father
 */
@RestController
@RequestMapping("/exams")
public class ExamComposerController {
    private final RestTemplate restTemplate;
    private final MeterRegistry meterRegistry;
    private final IntegrationFileGateway fileGateway;
    private int number = 1;


    @Autowired
    public ExamComposerController(@LoadBalanced RestTemplate restTemplate, MeterRegistry meterRegistry, IntegrationFileGateway fileGateway) {
        this.restTemplate = restTemplate;
        this.meterRegistry = meterRegistry;
        this.fileGateway = fileGateway;
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
        fileGateway.writeToFile(examSpec.entrySet().stream().findFirst().orElseThrow().getKey().toLowerCase(), new Date().toString() + ": " + examSpec.toString());
        List<Section> sections = examSpec.entrySet().stream().map(entry -> {
                String title = entry.getKey();
                String url = getServiceUrl(title, entry.getValue());
                Timer.Sample sample = Timer.start(meterRegistry);
                    Exercise[] exercises = restTemplate.getForObject(url, Exercise[].class);
                sample.stop(meterRegistry.timer("exam_request_provider","provider-tag", title));
                assert exercises != null;
                return Section.builder().exercises(Arrays.asList(exercises)).title(title).build();
            }).collect(toList());

        return Exam.builder().sections(sections).title("Best exam #" + number++).build();
    }

    public String getServiceUrl(String provider, int amount) {
        return "http://" + provider + "/api/questions/random?amount=" + amount;
    }
}
