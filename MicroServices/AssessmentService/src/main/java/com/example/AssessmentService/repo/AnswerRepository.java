package com.example.AssessmentService.repo;

import com.example.AssessmentService.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
