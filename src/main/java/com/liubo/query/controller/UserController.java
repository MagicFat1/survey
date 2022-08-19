package com.liubo.query.controller;

import com.liubo.query.entity.*;
import com.liubo.query.mapper.OptionDao;
import com.liubo.query.mapper.ScoresDao;
import com.liubo.query.mapper.UserDao;
import com.liubo.query.service.*;
import com.liubo.query.utils.MapParameter;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/inform")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private OptionService optionService;

    @Autowired
    private OptionDao optionDao;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private ScoresDao scoresDao;

    String userId;

    @PostMapping("/saveInform")
    public int save(@RequestBody User user) {
        //新增或者更新用户信息
        return userService.save(user);
    }

    //查询所有信息
    @GetMapping
    public List<User> index() {
        return userDao.findAll();
    }

    //分页查询+条件查询
    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        List<User> data = userDao.selectPage(pageNum, pageSize);
        Integer total = userDao.coutAll();
        Map<String, Object> res = new HashMap<>();
        res.put("total",total);
        res.put("data", data);
        return res;
    }

    /**
     * 向用户展示第i套问卷
     * @param surveyId
     * @return
     */
    @GetMapping("/showSurvey")
    public List<Question> showSurvey(@RequestParam Integer surveyId) {
        List<Question> questions = questionService.findAllBySurveyId(surveyId);
        for (Question question : questions) {
            Integer questionId = question.getQuestionId();
            Map<String, Object> map = MapParameter.getInstance().add("surveyId", surveyId)
                    .add("questionId",questionId ).getMap();
            List<Option> details = optionDao.details(map);
            question.setOptions(details);
        }
        return questions;
    }

    /**
     * 用户提交所有答案
     * @param maps
     * @return
     */
    @PostMapping("/submit")
    public List<Scores> submit(@RequestBody Map<String, Object> maps) {
        Integer surveyId = (Integer) maps.get("surveyId");
        List<Answer> answers = new ArrayList<>();
        List<Scores> scores = new ArrayList<>();
        List<Map<String, Object>> listAnswers = (List<Map<String, Object>>) maps.get("answers");
        for (Map<String, Object> listAnswer : listAnswers) {
            Answer answer = new Answer();
            answer.setQuestionId((Integer) listAnswer.get("questionId"));
            answer.setSurveyId((Integer) listAnswer.get("surveyId"));
            answer.setResult(Optional.ofNullable((String) listAnswer.get("result")).orElse("空"));
            answer.setType((Integer) listAnswer.get("type"));
            answer.setUserId((String) maps.get("userId"));
            userId = (String) maps.get("userId");
            answers.add(answer);
        }
        User user = new User();
        user.setUsername((String) maps.get("name"));
        user.setAge((String) maps.get("age"));
        user.setCardId((String) maps.get("userId"));
        userService.save(user);
        answerService.submit(answers);
        List<String> ruleTitles = scoreService.returnTitles(MapParameter.getInstance().
                                                            add("surveyId",surveyId).getMap());
        if (ruleTitles != null && !ruleTitles.isEmpty()) {
            for (String ruleTitle : ruleTitles) {
                Scores score = new Scores();
                score.setTitle(ruleTitle);
                score.setScore(getScore(MapParameter.getInstance().add("surveyId",surveyId).add("title",ruleTitle).getMap()));
                score.setRemark(getRuleRemark(surveyId,ruleTitle));
                score.setUserId(userId);
                scores.add(score);
                scoresDao.create(score);
            }
            return scores;
        } else
            return scores;
    }


    /**
     *创建算分规则
     * @param maps
     * @return
     */
    @PostMapping("/createScoreRule")
    public Integer createScoreRule(@RequestBody List<Map<String, Object>> maps) {
        int i = 0;
        for (Map<String, Object> map : maps) {
            String title = (String) map.get("title");
            Integer surveyId = (Integer) map.get("surveyId");
            List<Integer> questionIds = (List<Integer>) map.get("questionIds");
            String remark = (String) map.get("remark");
            Float factor = Float.parseFloat(map.get("factor").toString());
            Score score = new Score();
            score.setTitle(title);
            score.setSurveyId(surveyId);
            score.setFactor(factor);
            score.setRemark(remark);

            for (Integer questionId : questionIds) {
                score.setQuestionId(questionId);
                scoreService.create(score);
                i++;
            }
        }
        return i;
    }


    public Float getScore(Map<String, Object> map) {
        int sum = 0;
        Integer surveyId = (Integer) map.get("surveyId");
        String title = (String) map.get("title");
        List<Integer> questionIds = scoreService.returnQuestionIds(map);
        for (Integer questionId : questionIds) {
            sum = sum + optionService.getScore(surveyId, questionId,
                    answerService.getResult(userId, surveyId, questionId));
        }
        Float factor = scoreService.returnFactor(map);
        return sum * factor;
    }

    /*@GetMapping("/getRuleTitles")
    public List<String > getRuleTitles(@RequestBody Map<String, Object> map) {
        return scoreService.returnTitles(map);
    }*/

    public String getRuleRemark(Integer surveyId, String title) {
        return Optional.ofNullable(scoreService.returnRemarks(MapParameter.getInstance().add("title",title)
                .add("surveyId",surveyId).getMap()).get(0)).orElse("");
    }


}
