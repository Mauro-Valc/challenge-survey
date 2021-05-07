package com.robinfood.api.survey;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * RobinFood Challenge
 * <p>
 * Project that exposes a REST API of survey
 * 
 * @author mauro.valc@gmail.com
 *
 */
@EnableSwagger2
@SpringBootApplication
public class ChallengeSurveyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeSurveyApplication.class, args);
	}
	
	   @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.robinfood.api.survey.rest"))
	                .build().useDefaultResponseMessages(false).apiInfo(apiInfo());
	    }
	    
	    private ApiInfo apiInfo() {
	        return new ApiInfo("survey API REST", "Documentation of the API REST to survey ", "1.0",
	                "Visita https://example.com/terms", new Contact("robinfood", "www.robinfood.com", "mauro.valc@gmail.com"),
	                "License", "www.robinfood.com/license", Collections.emptyList());
	    }

}
