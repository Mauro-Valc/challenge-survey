package com.robinfood.api.survey.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.robinfood.api.survey.business.SurveyBusiness;
import com.robinfood.api.survey.domain.DetailSurveyDTO;
import com.robinfood.api.survey.domain.SurveyDTO;
import com.robinfood.api.survey.domain.SurveyRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET })
@Api("Api for Survey")
public class SurveyRest {
    
    private SurveyBusiness business;
    
    public SurveyRest(SurveyBusiness business) {
        this.business = business;
    }
    
    @ApiOperation(value = "Allows find all survey", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The survey was find successful", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "The data was incorrect", response = ResponseEntity.class) })
    @GetMapping(value = "/survey", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SurveyDTO>> getSurvey() {
        return this.business.getSurvey();
    }
    
    @ApiOperation(value = "Allows find survey detail by id", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The survey detail by id was find successful", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "The data was incorrect", response = ResponseEntity.class) })
    @GetMapping(value = "/survey", params = "id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DetailSurveyDTO> getSurvey(@RequestParam(name = "id") Long id) {
        return this.business.getSurvey(id);
    }
    
    @ApiOperation(value = "Allows send survey", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "survey send successful", response = ResponseEntity.class),
            @ApiResponse(code = 403, message = "The data was incorrect", response = ResponseEntity.class) })
    @PostMapping(value = "/survey", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> sendSurvey(
            @ApiParam(value = "Json object with answer", name = "SurveyRequestDTO", required = true, type = "JSON")@Valid@RequestBody(required = true)SurveyRequestDTO survey) {
        return this.business.sendSurvey(survey);
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
