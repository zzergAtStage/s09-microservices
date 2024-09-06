package org.zergatstage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zergatstage.DTO.ExamSubmissionDTO;
import org.zergatstage.model.User;
import org.zergatstage.repository.UserRepository;
import org.zergatstage.services.ExamService;
import org.zergatstage.services.UserService;

/**
 * @author father
 */
@RestController
@RequestMapping("/exam")
public class ExamController {

    private final ExamService examService;
    private final UserService userService;

    public ExamController(ExamService examService, UserRepository userRepository, UserService userService) {
        this.examService = examService;
        this.userService = userService;
    }

    @PostMapping("/submit")
    public ResponseEntity<Integer> submitExam(@RequestBody ExamSubmissionDTO submission) {
        int totalScore = examService.gradeExam(submission);
        return ResponseEntity.ok(totalScore); // Return the total score of the exam
    }

    @PostMapping("/user")
    public ResponseEntity<User> registerUser(@RequestParam String username){
        User user = userService.getUserByUsername(username);
        if (user == null) {
            user = userService.registerUser(username);
        }
        assert user != null;
        return ResponseEntity.ok(user);
    }
}
