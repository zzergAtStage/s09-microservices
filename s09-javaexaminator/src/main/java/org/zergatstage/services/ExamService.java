package org.zergatstage.services;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zergatstage.DTO.ExamSubmissionDTO;
import org.zergatstage.DTO.UserAnswerDTO;
import org.zergatstage.model.*;
import org.zergatstage.repository.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author father
 */
@Service
public class ExamService {

    @Autowired
    @Setter
    private JavaQuizRepository questionRepository;

    @Autowired
    @Setter
    private ExamRepository examRepository;

    @Autowired
    @Setter
    private ExamSectionRepository examSectionRepository;

    @Autowired
    @Setter
    private UserAnswerRepository userAnswerRepository;

    @Autowired
    @Setter
    private UserRepository userRepository;

    @Autowired
    @Setter
    private IntegrationFileGateway fileGateway;

    public void writeQuestions(List<JavaQuizQuestion> questions) {
        fileGateway.writeToFile("Sample_write_" + new Date().toString() + ": ", questions.toString());

    }

    /**
     * Grades the answers submitted by the user and stores the results.
     * @param submission The submitted answers and session info
     * @return The total score for the exam.
     */
    public int gradeExam(ExamSubmissionDTO submission) {
        // Retrieve the user and create a new exam entry
        User user = userRepository.findById(submission.getUserId()).orElseThrow();
        Exam exam = new Exam();
        exam.setSessionId(submission.getSessionId());
        exam.setUser(user);
        exam.setExamDate(LocalDateTime.now());
        exam = examRepository.save(exam);

        int totalScore = 0;

        // Iterate through each section
        for (Map.Entry<String, List<UserAnswerDTO>> sectionEntry : submission.getSectionAnswers().entrySet()) {
            ExamSection section = new ExamSection();
            section.setSectionName(sectionEntry.getKey());
            section.setExam(exam);
            section = examSectionRepository.save(section);

            // Grade each question in the section
            for (UserAnswerDTO answerDTO : sectionEntry.getValue()) {
                JavaQuizQuestion question = questionRepository.findById(answerDTO.getQuestionId()).orElseThrow();
                UserAnswer userAnswer = new UserAnswer();
                userAnswer.setSection(section);
                userAnswer.setQuestion(question);
                userAnswer.setUserAnswer(answerDTO.getAnswer());

                // Check if the answer is correct
                if (question.getCorrectAnswer().equalsIgnoreCase(answerDTO.getAnswer())) {
                    userAnswer.setCorrect(true);
                    userAnswer.setPointsAwarded(question.getPoints()); // Assuming each question has points
                    totalScore += question.getPoints();
                } else {
                    userAnswer.setCorrect(false);
                    userAnswer.setPointsAwarded(0);
                }

                userAnswerRepository.save(userAnswer);
            }
        }

        return totalScore;
    }
}
