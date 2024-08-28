package com.example.AssessmentService.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "setid")
    private Long setid;

    @Column(name = "set_name",unique = true)
    private String setName;

    private String domain;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "set_question_map", // Name of the join table
            joinColumns = @JoinColumn(name = "set_id"), // Foreign key for the Assessment entity
            inverseJoinColumns = @JoinColumn(name = "question_id") // Foreign key for the Question entity
    )
    private List<Question> questions;
}
