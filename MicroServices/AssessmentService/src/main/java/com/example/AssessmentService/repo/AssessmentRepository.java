package com.example.AssessmentService.repo;

import com.example.AssessmentService.model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
    Optional<Assessment> findBySetName(String setName);
    Optional<Assessment> findBySetid(long setid);


}