package org.zergatstage.examinator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author father
 */

@RestController
@RequestMapping("/exams-direct")
public class ExamDirectController extends ExamComposerController {
    @Autowired
    public ExamDirectController(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public String getServiceUrl(Map.Entry<String, Integer> entry) {
        return "http://provider-" + entry.getKey() + ":8080/exercise/random?amount=" + entry.getValue();
    }
}
