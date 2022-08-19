package com.liubo.query.service;

import com.liubo.query.entity.Survey;
import com.liubo.query.mapper.SurveyDao;
import com.liubo.query.utils.BeanMapUtils;
import com.liubo.query.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;


@Component("SurveyService")
@Service
public class SurveyService {

    @Autowired
    private SurveyDao surveyDao;

    /**
     * 创建问卷
     * @param survey
     * @return
     */
    public int create(Survey survey) {
        return surveyDao.create(survey);
    }

    /**
     * 删除问卷
     * @param id
     * @return
     */
    public int delete(Integer id) {
        return surveyDao.delete(MapParameter.getInstance().add("id", id).getMap());
    }

    /**
     * 更新问卷
     * @param survey
     * @return
     */
    public int update(Survey survey) {
        MapParameter instance = MapParameter.getInstance();
        instance.put(BeanMapUtils.beanToMap(survey)).add("id", survey.getId());
        return surveyDao.update(instance.getMap());
        /*return surveyDao.update(MapParameter.getInstance().put(BeanMapUtils.beanToMap(survey)).
                add("id", survey.getId()).getMap());*/
    }

    /**
     * 通过问卷标题查询id
     * @param map
     * @return
     */
    public int getSurveyId(Map<String, String> map) {
        return surveyDao.getSurveyId(map);
    }

    /**
     * 自动返回刚创建的surveyId
     * @return
     */
    public int returnSurveyId(){
        return surveyDao.returnSurveyId();
    }

    /**
     * 返回问卷title
     * @param map
     * @return
     */
    public String returnSurveyTitle(Map<String, Integer> map) {
        return surveyDao.returnTitle(map);
    }
}
