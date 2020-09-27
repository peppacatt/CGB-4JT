package com.jt.controller;

import com.jt.pojo.ItemCate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ItemCateController {

    @RequestMapping("/queryItemName")
    public String findItemCatNameById(Long itemCatId){
        return "";
    }

}
