package com.example.quizapp.controller;

import com.example.quizapp.dto.AnswerDTO;
import com.example.quizapp.dto.QuestionDTO;
import com.example.quizapp.model.Question;
import com.example.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/questions")
    public List<QuestionDTO> getAllQuestions() {
        return quizService.getQuestions();
    }

    @PostMapping("/submit")
    public Map<String, Object> submitAnswers(@RequestBody List<AnswerDTO> answers) {
        return quizService.evaluate(answers);
    }

    @PostMapping("/question")
    public Question createQuestion(@RequestBody Question question) {
        return quizService.createQuestion(question);
    }

    @DeleteMapping("/question/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        quizService.deleteQuestion(id);
    }
}
