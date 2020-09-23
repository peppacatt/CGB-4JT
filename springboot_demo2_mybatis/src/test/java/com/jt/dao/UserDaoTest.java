package com.jt.dao;

import com.jt.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

}
