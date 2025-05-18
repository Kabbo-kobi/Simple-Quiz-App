package com.example.quizapp.service;

import com.example.quizapp.dto.AnswerDTO;
import com.example.quizapp.dto.QuestionDTO;
import com.example.quizapp.model.Question;
import com.example.quizapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private QuestionRepository questionRepo;

    public List<QuestionDTO> getQuestions() {
        return questionRepo.findAll().stream().map(q -> {
            QuestionDTO dto = new QuestionDTO();
            dto.setId(q.getId());
            dto.setText(q.getText());
            dto.setOptions(q.getOptions());
            return dto;
        }).collect(Collectors.toList());
    }

    public Map<String, Object> evaluate(List<AnswerDTO> answers) {
        int score = 0;
        List<Map<String, Object>> results = new ArrayList<>();

        for (AnswerDTO ans : answers) {
            Optional<Question> question = questionRepo.findById(ans.getQuestionId());
            if (question.isPresent()) {
                boolean correct = question.get().getCorrectOptionId().equals(ans.getSelectedOptionId());
                if (correct) score++;
                results.add(Map.of(
                    "question", question.get().getText(),
                    "correct", correct
                ));
            }
        }

        return Map.of("score", score, "results", results);
    }

    public Question createQuestion(Question q) {
        return questionRepo.save(q);
    }

    public void deleteQuestion(Long id) {
        questionRepo.deleteById(id);
    }
}
