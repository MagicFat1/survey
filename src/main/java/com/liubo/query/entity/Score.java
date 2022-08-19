package com.liubo.query.entity;

import lombok.Data;

@Data
public class Score {
    private Integer id;
    private String title;
    private Integer surveyId;
    private Integer questionId;
    private Float factor;
    private String remark;
}
