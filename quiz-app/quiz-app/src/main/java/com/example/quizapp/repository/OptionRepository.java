package com.example.quizapp.repository;

import com.example.quizapp.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {}
