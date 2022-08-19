package com.liubo.query.service;

import com.liubo.query.entity.Option;
import com.liubo.query.mapper.OptionDao;
import com.liubo.query.utils.BeanMapUtils;
import com.liubo.query.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component("OptionService")
@Service
public class OptionService {

    @Autowired
    private OptionDao optionDao;

    /**
     * 创建问题的选项
     * @param option
     * @return
     */
    public int create(Option option) {
        return optionDao.create(option);
    }

    /**
     * 查询所有问题选项
     * @return
     */
    public List<Option> findAll() {
        return optionDao.findAll();
    }

    /**
     * 更新问题选项
     * @param option
     * @return
     */
    public int update(Option option) {
        return optionDao.update(MapParameter.getInstance().put(BeanMapUtils.beanToMap(option)).getMap());
    }

    /**
     * 删除某个选项
     * @param id
     * @return
     */
    public int delete(Integer id) {
        return optionDao.delete(MapParameter.getInstance().put("id", id).getMap());
    }

    /**
     * 查询某个选项
     * @param id
     * @param questionId
     * @param orderby
     * @return
     */
    public Option detail(Integer id, Integer questionId, Integer orderby) {
        return optionDao.detail(MapParameter.getInstance().put("id", id)
                .put("questionId",questionId).put("orderby",orderby).getMap());
    }

    /**
     * 查询答案对应的分数
     * @param surveyId
     * @param questionId
     * @param result
     * @return
     */
    public Integer getScore(Integer surveyId, Integer questionId, String result) {
        int score = 0;
        score = Optional.ofNullable(optionDao.score(MapParameter.getInstance().add("surveyId", surveyId).
                add("questionId", questionId).add("opt", result).getMap())).orElse(0) ;
        return score;
    }
}
