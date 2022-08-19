package com.liubo.query.service;

import com.liubo.query.entity.Question;
import com.liubo.query.entity.Survey;
import com.liubo.query.entity.User;
import com.liubo.query.mapper.QuestionDao;
import com.liubo.query.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component("UserService")
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private QuestionService questionService;

    public int save(User user) {
        /*if(user.getCardId() == null) {*/
           return userDao.insert(user);
       /* }else {
           return userDao.update(user);
        }*/
    }

    public List<Question> showSurvey(Integer surveyId) {
        return questionService.findAllBySurveyId(surveyId);
    }
}
