package com.liubo.query.mapper;

import com.liubo.query.entity.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface AnswerDao {

    /**
     * 提交答案表信息
     * @param answer
     * @return
     */
    int create(Answer answer);

    /**
     * 删除答案
     * @param paramMap
     * @return
     */
    int deleteById(Map<String, Object> paramMap);

    /**
     * 更新答案
     * @param paramMap
     * @return
     */
    int update(Map<String, Object> paramMap);

    /**
     * 查找该问卷的答案
     * @param paramMap
     * @return
     */
    List<Answer> query(Map<String, Object> paramMap);

    /**
     *查询某问卷某题的答案
     * @param paramMap
     * @return
     */
    Answer detail(Map<String, Object> paramMap);

    /**
     * 查询某人某题的答案
     * @param paramMap
     * @return
     */
    String getAnswer(Map<String, Object> paramMap);
}
