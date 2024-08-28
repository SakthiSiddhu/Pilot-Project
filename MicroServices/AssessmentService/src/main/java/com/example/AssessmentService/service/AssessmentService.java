package com.example.AssessmentService.service;


import com.example.AssessmentService.dto.*;
import com.example.AssessmentService.exception.ResourceNotFoundException;
import com.example.AssessmentService.model.Answer;
import com.example.AssessmentService.model.Assessment;
import com.example.AssessmentService.model.Question;
import com.example.AssessmentService.repo.AnswerRepository;
import com.example.AssessmentService.repo.AssessmentRepository;
import com.example.AssessmentService.repo.QuestionRepository;
import com.example.AssessmentService.utils.AssessmentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class  AssessmentService {

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    AssessmentUtil assessmentUtil;


    @Autowired
    private AnswerRepository answerRepository;

    private final String setNameIsInvalid = "Set name is invalid";
    private final String questionIdIsInvalid = "Question id is invalid";

    @Transactional
    public Assessment createAssessment(AssessmentDTO assessmentRequest) {
        Assessment assessment = assessmentUtil.MapToAssessment(assessmentRequest);
        return assessmentRepository.save(assessment);
    }


    public List<Assessment> getAllAssessments() {
        return assessmentRepository.findAll();
    }

    @Transactional
    public String updateQuestion(Long setId, Long questionId, List<AnswerDTO> answerDtos) {
        // Fetch the assessment by ID
        Assessment assessment = assessmentRepository.findById(setId)
                .orElseThrow(() -> new ResourceNotFoundException(setNameIsInvalid));

        // Find the question by question ID within the assessment
        Question questionToUpdate = assessment.getQuestions().stream()
                .filter(q -> q.getQuestionId()==questionId)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(questionIdIsInvalid));

        // Add answers if provided
        if (answerDtos != null && !answerDtos.isEmpty()) {
            List<Answer> answers = answerDtos.stream()
                    .map(answerDto -> {
                        Answer answer = new Answer();
                        answer.setValue(answerDto.getValue());
                        answer.setSuggestion(answerDto.getSuggestion());
                        answer.setQuestion(questionToUpdate);
                        return answer;
                    })
                    .collect(Collectors.toList());

            // Ensure the existing answers list is initialized
            List<Answer> existingAnswers = questionToUpdate.getAnswers();
            if (existingAnswers == null) {
                existingAnswers = new ArrayList<>();
            }

            // Append the new answers to the existing ones
            existingAnswers.addAll(answers);
            questionToUpdate.setAnswers(existingAnswers);
        }

        // Save the updated assessment and question
        assessmentRepository.save(assessment);
        questionRepository.save(questionToUpdate);

        return "Question updated successfully";
    }


    @Transactional
    public Map<String, String> deleteQuestion(long setid, Long questionId) {
        Map<String, String> response = new HashMap<>();
        Assessment assessment = assessmentRepository.findById(setid).orElse(null);
        if (assessment == null) {
            //throw new ResourceNotFoundException("Assessment not found");

            throw new ResourceNotFoundException(setNameIsInvalid);

        }

        Optional<Question> questionToDelete = assessment.getQuestions().stream()
                .filter(q -> q.getQuestionId()==questionId)
                .findFirst();


        if (!questionToDelete.isPresent()) {

            throw new ResourceNotFoundException("question is not found");
        }

        assessment.getQuestions().remove(questionToDelete.get());
        questionRepository.deleteById(questionToDelete.get().getQuestionId());
        assessmentRepository.save(assessment);

        response.put("message", "Question deleted successfully");
        return response;
    }



    public List<Question> getQuestionsSetName(String setName) {
        Assessment assessment = assessmentRepository.findBySetName(setName).orElse(null);
        if (assessment == null)
            throw new ResourceNotFoundException("set name is invalid");
        return assessment.getQuestions();

    }

    public Optional<Question> fetchques(Long qid) {
        Optional<Question> question = questionRepository.findByQuestionId(qid);

        return question;


    }

    public List<Question> getQuestionsSetId(long setid) {
        Assessment assessment = assessmentRepository.findBySetid(setid).orElse(null);
        if (assessment == null)
            throw new ResourceNotFoundException("set id is invalid");
        return assessment.getQuestions();

    }

}