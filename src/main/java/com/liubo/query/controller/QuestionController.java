package com.liubo.query.controller;

import com.liubo.query.entity.Question;
import com.liubo.query.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/query")
    public List<Question> index(){
        List<Question> all = questionService.findAll();
        return all;
    }

    /**
     * 创建问题和选项
     * @param question
     * @return
     */
    @PostMapping("/create")
    public int create(@RequestBody Question question) {
        return questionService.create(question);
    }

    @PostMapping("/create_s")
    public int create(@RequestBody List<Question> questions) {
        int j = 0;
        for (Question question : questions) {
            questionService.create(question);
            j++;
        }
        return j;
    }

    @PostMapping("/update")
    public int updateById(@RequestBody Question question) {
        return questionService.update(question);
    }

    @PostMapping("/delete")
    public int deleteById(@RequestBody Map<String, Integer> map) {
        return questionService.delete(map.get("id"));
    }

}
