package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired(required = false)
    private UserService userService;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        System.out.println(userList);
        return "userList";
    }

    @RequestMapping("/userListAjax")
    public String userListAjax(){
        return "userListAjax";
    }

    @RequestMapping("/findAjax")
    @ResponseBody
    public List<User> findAjax(){
        return userService.findAll();
    }

}


