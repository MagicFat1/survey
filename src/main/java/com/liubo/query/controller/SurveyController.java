package com.liubo.query.controller;

import com.liubo.query.entity.Survey;
import com.liubo.query.entity.User;
import com.liubo.query.mapper.SurveyDao;
import com.liubo.query.service.SurveyService;
import com.liubo.query.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private SurveyDao surveyDao;

    //查询所有信息
    @GetMapping("/query")
    public List<Survey> index() {
        return surveyDao.findAll();
    }

    /**
     * 创建问卷标题，并返回问卷id
     * @param survey
     * @return
     */
    @PostMapping("/create")
    public int save(@RequestBody Survey survey) {
        surveyService.create(survey);
        return surveyService.returnSurveyId();
    }

    @PostMapping("/update")
    public int update(@RequestBody Survey survey) {
        return surveyService.update(survey);
    }

    @PostMapping("/delete")
    public int deleteById(@RequestBody Map<String, Integer> map) {
        return surveyService.delete(map.get("id"));
    }

    @GetMapping("/getSurveyId")
    public int getSurveyId(@RequestBody Map<String, String> map) {
        return surveyService.getSurveyId(map);
    }

    @GetMapping("/getSurveyTitle")
    public String getSurveyTitle(@RequestParam Integer surveyId) {
        Map<String, Integer> map = new HashMap<>();
        map.put("surveyId",surveyId);
        return surveyService.returnSurveyTitle(map);
    }

}
