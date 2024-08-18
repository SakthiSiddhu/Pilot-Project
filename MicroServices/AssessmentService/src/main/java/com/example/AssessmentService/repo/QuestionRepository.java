package com.example.AssessmentService.repo;

import com.example.AssessmentService.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
   Optional<Question> findByQuestionId(Long questionId);
}
