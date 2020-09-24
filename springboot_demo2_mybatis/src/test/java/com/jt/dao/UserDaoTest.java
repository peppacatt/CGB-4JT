package com.jt.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
public class UserDaoTest {

    @Autowired(required = false)
    private UserDao userDao;

    @Test
    public void findAllTest(){
        List<User> userList = userDao.findAll();
        for(User temp:userList){
            System.out.println(temp);
        }
    }

    @Test
    public void testSelect01(){
        List<User> userList = userDao.selectList(null);
        for(User temp:userList){
            System.out.println(temp);
        }
    }

    @Test
    public void testSelect02(){
        User user = userDao.selectById(11);
        System.out.println(user);
    }

    @Test
    public void testSelect03(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "小乔")
                    .ge("age", 18);
        List<User> userList = userDao.selectList(queryWrapper);
        for(User temp: userList){
            System.out.println(temp);
        }
    }

    @Test
    public void testSelect05(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeLeft("name", "精")
                    .eq("sex", "女");
        List<User> users = userDao.selectList(queryWrapper);
        for(User temp: users){
            System.out.println(temp);
        }
    }

    @Test
    public void testSelect06(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("name", "age");

    }

}
