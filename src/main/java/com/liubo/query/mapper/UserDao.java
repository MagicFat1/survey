package com.liubo.query.mapper;

import com.liubo.query.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {

    @Select("SELECT * FROM inform")
    List<User> findAll();


    int insert(User user);

    int update(User user);

    @Select("select * from inform limit #{pageNum}, #{pageSize}")
    List<User> selectPage(Integer pageNum, Integer pageSize);

    @Select("select count(id) from inform")
    Integer coutAll();
}
