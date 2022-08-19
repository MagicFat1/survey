package com.liubo.query.service;

import com.liubo.query.entity.Answer;
import com.liubo.query.mapper.AnswerDao;
import com.liubo.query.mapper.OptionDao;
import com.liubo.query.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component("AnswerService")
@Service
public class AnswerService {

    @Autowired
    private AnswerDao answerDao;

    /**
     * 提交答案
     * @param listOpt
     * @return
     */
    public Integer submit(List<Answer> listOpt) {
        int flag = 0;
        for (Answer answer : listOpt) {
            flag += answerDao.create(answer);
        }
        return flag;
    }

    public String getResult(String userId, Integer surveyId, Integer questionId) {
        return answerDao.getAnswer(MapParameter.getInstance().add("userId", userId).add("surveyId",surveyId)
                            .add("questionId", questionId).getMap());
    }
}
