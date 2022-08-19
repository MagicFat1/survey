package com.liubo.query.mapper;

import com.liubo.query.entity.Survey;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface SurveyDao {

    @Insert("INSERT into survey(id, title) VALUES (#{id}, #{title})")
    int create(Survey survey);

    int delete(Map<String, Object> paramMap);

    int update(Map<String, Object> paramMap);

    @Select("SELECT * FROM survey")
    List<Survey> findAll();

    int getSurveyId(Map<String, String> paramMap);

    @Select("SELECT @@IDENTITY")
    int returnSurveyId();

    String returnTitle(Map<String,Integer> paramMap);

}