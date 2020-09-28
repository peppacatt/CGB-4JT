package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.vo.EasyUITable;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.service.ItemService;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/query")
	public EasyUITable findItemByPage(Integer page, Integer rows){
		return itemService.findItemByPage(page,rows);
	}

//	@RequestMapping("/save")
//	public SysResult saveItem(Item item){
//		try{
//			itemService.saveItem(item);
//			return SysResult.success();
//		}catch (Exception e){
//			e.printStackTrace();
//			return SysResult.fail();
//		}
//	}
	@RequestMapping("/save")
	public SysResult saveItem(Item item){
		itemService.saveItem(item);
		return SysResult.success();
	}
	
	
}
