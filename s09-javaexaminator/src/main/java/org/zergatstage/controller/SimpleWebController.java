package org.zergatstage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zergatstage.DTO.ExamSubmissionDTO;
import org.zergatstage.model.Exam;
import org.zergatstage.model.User;
import org.zergatstage.services.ExamService;
import org.zergatstage.services.UserService;

import java.util.List;

/**
 * @author father
 */

@Controller
public class SimpleWebController {


    private final ExamService examService;
    private final UserService userService;


    public SimpleWebController(ExamService examService, UserService userService) {
        this.examService = examService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "index.html";
    }

    @PostMapping("/quiz")
    public String quiz(@RequestParam String username, Model m, RedirectAttributes ra) {
        if (username.equals("")) {
            ra.addFlashAttribute("warning", "You must enter your name");
            return "redirect:/";
        }
        User user = userService.registerUser(username);
        Exam qForm = examService.getExam(3, 6);
        qForm.setUser(user);
        m.addAttribute("qForm", qForm);

        return "quiz.html";
    }

    @PostMapping("/submitQuiz")
    public String submit(@ModelAttribute ExamSubmissionDTO qForm, Model m) {
        //TODO: recalculate of results

        return "result.html";
    }

    @GetMapping("/score")
    public String score(Model m) {
        List<Exam> sList = List.of(examService.getSubmittedExamByUser(m.getAttribute("UserName").toString()));
        m.addAttribute("sList", sList);

        return "scoreboard.html";
    }

    @GetMapping("/code")
    public String getCode() {
        return "templates-code.html";
    }
}
