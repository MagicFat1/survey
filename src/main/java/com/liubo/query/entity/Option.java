package com.liubo.query.entity;

import lombok.Data;

@Data
public class Option {
    private Integer id;
    private Integer questionId;
    private Integer surveyId;
    private String opt;
    private String describe;
    private Integer orderby;
    private Integer score;

}
