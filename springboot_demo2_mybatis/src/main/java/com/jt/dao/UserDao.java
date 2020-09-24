package com.jt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Mapper
public interface UserDao extends BaseMapper<User> {

    @Select("SELECT * FROM user")
    List<User> findAll();

}
