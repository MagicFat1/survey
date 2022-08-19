package com.liubo.query.mapper;

import com.liubo.query.entity.Scores;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ScoresDao {

    int create(Scores scores);
}
