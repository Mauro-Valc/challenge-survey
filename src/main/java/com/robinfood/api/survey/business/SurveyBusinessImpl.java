package com.robinfood.api.survey.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.robinfood.api.survey.domain.DetailSurveyDTO;
import com.robinfood.api.survey.domain.OptionDTO;
import com.robinfood.api.survey.domain.QuestionDTO;
import com.robinfood.api.survey.domain.SurveyDTO;
import com.robinfood.api.survey.domain.SurveyRequestDTO;
import com.robinfood.api.survey.model.Answer;
import com.robinfood.api.survey.model.Survey;
import com.robinfood.api.survey.model.User;
import com.robinfood.api.survey.model.UserAnswer;
import com.robinfood.api.survey.repository.AnswerRepository;
import com.robinfood.api.survey.repository.SurveyRepository;
import com.robinfood.api.survey.repository.UserAnswerRepository;
import com.robinfood.api.survey.repository.UserRepository;

@Service
public class SurveyBusinessImpl implements SurveyBusiness {

    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private AnswerRepository optionRepository;
    @Autowired
    private UserAnswerRepository userAnswerRepository;
    @Autowired
    private UserRepository userRepository;

    public SurveyBusinessImpl() {
    }

    /** Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(SurveyBusinessImpl.class);

    @Override
    public ResponseEntity<List<SurveyDTO>> getSurvey() {
        LOGGER.info("Init getSurvey");
        ResponseEntity<List<SurveyDTO>> response = null;
        List<SurveyDTO> surveys = new ArrayList<>();
        try {
            surveys = this.surveyRepository.findAllSurvey();
            response = new ResponseEntity<>(surveys, HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info("Finish mutantValidator with response: {}", response);
        return response;
    }

    @Override
    public ResponseEntity<DetailSurveyDTO> getSurvey(Long id) {
        LOGGER.info("Init getSurvey with id: {}", id);
        ResponseEntity<DetailSurveyDTO> response = null;
        DetailSurveyDTO detailSurvey = null;
        try {
            Optional<Survey> surveyResult = this.surveyRepository.findById(id);
            if (surveyResult.isPresent()) {
                Survey survey = surveyResult.get();
                detailSurvey = new DetailSurveyDTO(survey.getSurveyId(), survey.getName());
                List<QuestionDTO> questionsDTO = survey.getQuestion().stream().map(q -> {
                    QuestionDTO question = new QuestionDTO();
                    question.setId(q.getQuestionId());
                    question.setQuestion(q.getQuestion());
                    List<OptionDTO> optionsDTO = q.getAnswer().stream().map(o -> {
                        OptionDTO optionDTO = new OptionDTO();
                        optionDTO.setId(o.getAnswerId());
                        optionDTO.setValue(o.getValor());
                        return optionDTO;
                    }).collect(Collectors.toList());
                    question.setOptions(optionsDTO);
                    return question;
                }).collect(Collectors.toList());
                detailSurvey.setQuestions(questionsDTO);
                response = new ResponseEntity<>(detailSurvey, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOGGER.error("error", e);
            response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info("Finish mutantValidator with response: {}", response);
        return response;
    }

    @Override
    public ResponseEntity<Void> sendSurvey(SurveyRequestDTO survey) {
        LOGGER.info("Init ResponseEntity with survey: {}", survey);
        ResponseEntity<Void> response = null;
        User user = null;
        try {
            Optional<User> userResult = this.userRepository.findByEmail(survey.getEmail());
            user = userResult.isPresent() ? userResult.get() : new User(survey.getEmail(), survey.getName(), new Date());
            if (userResult.isPresent()) {
                user = userResult.get();
            } else {
                user = new User(survey.getEmail(), survey.getName(), new Date());
                user = userRepository.save(user);
            }
            List<Answer> answers = getAnswerById(survey.getAnswers());
            Date now = new Date();
            for (Answer answer : answers) {
                UserAnswer userAnswer = new UserAnswer(user, answer, now);
                this.userAnswerRepository.save(userAnswer);
            }
            response = new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("error", e);
            response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info("Finish ResponseEntity with response: {}", response);
        return response;
    }

    private List<Answer> getAnswerById(List<OptionDTO> answers) {
        List<Long> listId = new ArrayList<>();
        for (OptionDTO optionDTO : answers) {
            listId.add(optionDTO.getId());
        }
        return this.optionRepository.findOpstionsByListId(listId);
    }

}
