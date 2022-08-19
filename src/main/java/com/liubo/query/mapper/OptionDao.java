package com.liubo.query.mapper;

import com.liubo.query.entity.Option;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface OptionDao {

    /**
     * 创建问题选项
     * @param option
     * @return
     */
    int create(Option option);

    /**
     * 删除选项
     * @param paramMap
     * @return
     */
    int delete(Map<String, Object> paramMap);

    int deleteByQuestionId(Map<String, Object> paramMap);

    /**
     * 更新选项
     * @param paramMap
     * @return
     */
    int update(Map<String, Object> paramMap);

    /**
     * 在更新问题时更新选项
     * @param paramMap
     * @return
     */
    int updateByQuestion(Map<String, Object> paramMap);

    /**
     * 查询所有问题选项
     * @return
     */
    List<Option> findAll();

    /**
     * 查询某个问题的某个选项
     * @param paramMap
     * @return
     */
    Option detail(Map<String, Object> paramMap);

    /**
     * 查询某个问题的所有选项
     * @param paramMap
     * @return
     */
    List<Option> details(Map<String, Object> paramMap);

    /**
     * 查询某个选项的答案
     * @param paramMap
     * @return
     */
    Integer score(Map<String, Object> paramMap);
}
