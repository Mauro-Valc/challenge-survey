package com.robinfood.api.survey.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.robinfood.api.survey.model.Answer;
import com.robinfood.api.survey.model.Question;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("select o from Answer o where o.question = :q")
    Set<Answer> getByQuestion(@Param("q") Question q);

    @Query("select o from Answer o where o.answerId in (:listId)")
    List<Answer> findOpstionsByListId(@Param("listId") List<Long> listId);

}
