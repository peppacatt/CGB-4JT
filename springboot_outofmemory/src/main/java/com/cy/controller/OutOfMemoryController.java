package com.cy.controller;


import com.cy.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class OutOfMemoryController {

    private List<User> userList = new ArrayList<>();

    @GetMapping("/heap")
    public String heap(){
        int i = 0;
        while (true){
            userList.add(new User(i++, UUID.randomUUID().toString()));
        }
    }

}
