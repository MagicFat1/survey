package com.liubo.query.service;

import com.liubo.query.entity.Option;
import com.liubo.query.entity.Question;
import com.liubo.query.mapper.OptionDao;
import com.liubo.query.mapper.QuestionDao;
import com.liubo.query.utils.BeanMapUtils;
import com.liubo.query.utils.MapParameter;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.binding.MapperMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Component("QuestionService")
@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private OptionDao optionDao;

    /**
     * 创建问题与选项，如已有问题id则更新
     * @param question
     * @return
     */
    public int create(Question question) {
        /*int flag = 0;
        if(question.getId() != null) {
            flag = this.update(question);
            optionDao.deleteByQuestionId(MapParameter.getInstance().add("questionId", question.getId())
                    .add("surveyId",question.getSurveyId()).getMap());
        } else {
            flag = questionDao.create(question);
        }
        if (flag > 0) {
            List<Option> options = question.getOptions();
            int i = 0;
            for (Option option : options) {
                option.setSurveyId(question.getSurveyId());
                option.setQuestionId(question.getQuestionId());
                option.setOrderby(++i);
                optionDao.create(option);
            }
        }
        return questionDao.create(question);*/
        questionDao.create(question);
        List<Option> options = question.getOptions();
        int i = 0;
        for (Option option : options) {
            option.setSurveyId(question.getSurveyId());
            option.setQuestionId(question.getQuestionId());
            option.setOrderby(++i);
            optionDao.create(option);
        }
        return i;
    }

    /**
     * 展示所有问题
     * @return
     */
    public List<Question> findAll() {
        return questionDao.findAll();
    }

    /**
     * 展示某个问卷的所有问题
     * @param surveyId
     * @return
     */
    public List<Question> findAllBySurveyId(Integer surveyId) {
        Map<String, Object> map = MapParameter.getInstance().add("surveyId", surveyId).getMap();
        return questionDao.findAllBySurveyId(map);
    }

    /**
     * 更新某个问题，连同选项
     * @param question
     * @return
     */
    public int update(Question question) {
        questionDao.update(MapParameter.getInstance().
                                  put(BeanMapUtils.beanToMap(question)).addId(question.getId()).getMap());
        List<Option> options = question.getOptions();
        int i = 0;
        for (Option option : options) {
            option.setSurveyId(question.getSurveyId());
            option.setQuestionId(question.getQuestionId());
            option.setOrderby(++i);
            optionDao.updateByQuestion(MapParameter.getInstance().put(BeanMapUtils.beanToMap(option)).getMap());
        }
        return 1;
    }

    /**
     * 删除问题
     * @param id
     * @return
     */
    public int delete(Integer id) {
        return questionDao.delete(MapParameter.getInstance().add("id", id).getMap());
    }

    /**
     * 装配选项到Question中
     * @param surveyId
     */
    public void getOption(Integer surveyId) {
        List<Question> questions = questionDao.findAllBySurveyId(MapParameter.getInstance()
                .add("surveyId",surveyId).getMap());
        for (Question question : questions) {
            question.setOptions(optionDao.details(MapParameter.getInstance().add("surveyId",surveyId)
            .add("questionId",question.getId()).getMap()));
        }
    }
}
