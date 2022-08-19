package com.liubo.query.mapper;

import com.liubo.query.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface QuestionDao {

    int create(Question question);

    List<Question> findAll();

    List<Question> findAllBySurveyId(Map<String, Object> paramMap);

    int update(Map<String, Object> paramMap);

    int delete(Map<String, Object> paramMap);
}
