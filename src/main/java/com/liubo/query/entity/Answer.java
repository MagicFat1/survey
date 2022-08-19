package com.liubo.query.entity;

import lombok.Data;

@Data
public class Answer {
    private Integer id;
    private Integer surveyId;
    private Integer questionId;
    private String userId;
    private Integer type;
    private String result;

}
