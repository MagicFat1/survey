package com.liubo.query.entity;

import lombok.Data;

import java.util.List;

@Data
public class Question {

    //自增id
    private Integer id;

    //问题id
    private Integer questionId;

    //问卷id
    private Integer surveyId;

    //问题类型，0单选，1填空
    private Integer type;

    //0不必填，1必填
    private Integer required;

    //题干
    private String remark;

    //0不算分，1该题算分
    private Integer test;

    //题目选项
    private List<Option> options;

}
