package com.example.AssessmentService.repo;

import com.example.AssessmentService.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
