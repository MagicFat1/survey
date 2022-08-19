package com.liubo.query.service;

import com.liubo.query.entity.Score;
import com.liubo.query.mapper.ScoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("ScoreService")
@Service
public class ScoreService {

    @Autowired
    private ScoreDao scoreDao;


    /**
     * 创建算分规则
     * @param score
     * @return
     */
    public int create(Score score) {
        return scoreDao.create(score);
    }

    /**
     * 返回要运算的题号
     * @param map
     * @return
     */
    public List<Integer> returnQuestionIds(Map<String, Object> map) {
        return scoreDao.returnQuestionIds(map);
    }

    /**
     * 返回系数
     * @param map
     * @return
     */
    public Float returnFactor(Map<String, Object> map) {
        List<Float> factors = scoreDao.returnFactor(map);
        int size = factors.size();
        Optional<Float> sum = factors.stream().reduce((x, y) -> x + y);
        return sum.get() / size;
    }

    /**
     * 根据surveyId返回算分规则标题
     * @param map
     * @return
     */
    public List<String> returnTitles(Map<String, Object> map) {
        return scoreDao.returnTitles(map).stream().distinct().collect(Collectors.toList());
    }

    /**
     * 返回算分规则备注
     * @param map
     * @return
     */
    public List<String> returnRemarks(Map<String, Object> map) {
        return scoreDao.returnRemarks(map).stream().distinct().collect(Collectors.toList());
    }


}
