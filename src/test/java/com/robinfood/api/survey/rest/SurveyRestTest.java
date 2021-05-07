package com.robinfood.api.survey.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.robinfood.api.survey.business.SurveyBusiness;
import com.robinfood.api.survey.domain.OptionDTO;
import com.robinfood.api.survey.domain.SurveyRequestDTO;
import com.robinfood.api.survey.model.Answer;
import com.robinfood.api.survey.model.Question;
import com.robinfood.api.survey.model.Survey;
import com.robinfood.api.survey.repository.AnswerRepository;
import com.robinfood.api.survey.repository.QuestionRepository;
import com.robinfood.api.survey.repository.SurveyRepository;
import com.robinfood.api.survey.repository.UserAnswerRepository;
import com.robinfood.api.survey.repository.UserRepository;

@SpringBootTest
@ActiveProfiles("test")
class SurveyRestTest {

    private static final String SURVEY_PATH = "/survey";
    private static final String EMAIL = "test@tes.com";
    private static final String NAME = "test";
    private static final String ID_PARAM = "id";
    private static final String ID = "1";

    private MockMvc restMock;
    @Autowired
    private SurveyBusiness business;
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserAnswerRepository userAnswerRepository;
    @Autowired
    private UserRepository userRepository;
    private final Gson gson = new Gson();

    @BeforeEach
    public void setUp() {
        this.cleanDataBase();
        MockitoAnnotations.openMocks(this);
        this.restMock = MockMvcBuilders.standaloneSetup(new SurveyRest(this.business)).build();
    }

    private void cleanDataBase() {
        this.userAnswerRepository.deleteAll();
        this.answerRepository.deleteAll();
        this.questionRepository.deleteAll();
        this.surveyRepository.deleteAll();
        this.userRepository.deleteAll();
    }

    private void createSurvey() {
        Survey survey = new Survey();
        survey.setSurveyId(1L);
        survey.setName(NAME);
        survey = this.surveyRepository.save(survey);
        Question question = new Question();
        question.setQuestionId(1L);
        question.setQuestion(NAME);
        question.setSurvey(survey);
        question = this.questionRepository.save(question);
        Answer answer = new Answer();
        answer.setAnswerId(1L);
        answer.setValor(NAME);
        answer.setQuestion(question);
        answer = this.answerRepository.save(answer);
    }

    private SurveyRequestDTO createSurveyRequest() {
        SurveyRequestDTO request = new SurveyRequestDTO();
        request.setEmail(EMAIL);
        request.setName(NAME);
        List<OptionDTO> answers = new ArrayList<>();
        OptionDTO answer = new OptionDTO();
        answer.setId(1L);
        answer.setValue(NAME);
        answers.add(answer);
        request.setAnswers(answers);
        return request;
    }

    @Test
    void sendSurveyTest() throws Exception {
        this.createSurvey();
        this.restMock.perform(post(URI.create(SURVEY_PATH)).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(gson.toJson(this.createSurveyRequest()).getBytes())).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getSurveyByIdTest() throws Exception {
        this.createSurvey();
        this.restMock.perform(get(URI.create(SURVEY_PATH)).contentType(MediaType.APPLICATION_JSON_VALUE).param(ID_PARAM, ID))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getSurveyTest() throws Exception {
        this.createSurvey();
        this.restMock.perform(get(URI.create(SURVEY_PATH)).contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
                .andExpect(status().isOk());
    }
}
