package com.example.quizapp.model;

import jakarta.persistence.*;

@Entity
public class Option {
    @Id
    @GeneratedValue
    private Long id;
    private String text;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    // Getters and Setters
}
