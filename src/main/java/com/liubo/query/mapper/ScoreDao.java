package com.liubo.query.mapper;

import com.liubo.query.entity.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ScoreDao {

    int create(Score score);

    @Select("SELECT @@IDENTITY")
    int returnId();

    List<Integer> returnQuestionIds(Map<String, Object> paramMap);

    List<Float> returnFactor(Map<String, Object> paramMap);

    List<String> returnTitles(Map<String, Object> paramMap);

    List<String> returnRemarks(Map<String, Object> paramMap);
}
