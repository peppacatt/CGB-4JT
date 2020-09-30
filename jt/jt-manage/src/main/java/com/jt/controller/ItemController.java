package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.service.ItemService;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController //由于ajax调用 采用JSON串返回
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	/**
	 * url: http://localhost:8091/item/query?page=1&rows=20
	 * 请求参数:  page=1&rows=20
	 * 返回值结果: EasyUITable
	 */
	@RequestMapping("/query")
	public EasyUITable findItemByPage(Integer page,Integer rows){

		return itemService.findItemByPage(page,rows);
	}


	/**
	 * 业务需求:
	 * 		完成商品入库操作,如果成功应该返回VO对象
	 * 	url:/item/save
	 * 	参数: 整个表单数据
	 * 	返回值: SysResult Vo对象
	 * 	*/

	@RequestMapping("/save")
	public SysResult saveItem(Item item, ItemDesc itemDesc){

		itemService.saveItem(item,itemDesc);
		return SysResult.success();
		/*try {
			itemService.saveItem(item);
			return SysResult.success();

		}catch (Exception e){
			e.printStackTrace();
			return SysResult.fail();
		}*/
	}

	/**
	 * 商品修改操作
	 * 1.url地址: http://localhost:8091/item/update
	 * 2.参数:	  form表单提交
	 * 3.返回值:  SysResult对象
	 */
	@RequestMapping("/update")
	public SysResult updateItem(Item item,ItemDesc itemDesc){

		itemService.updateItem(item,itemDesc);
		return SysResult.success();
	}

	/**
	 * 业务: 商品删除
	 * url地址: http://localhost:8091/item/delete
	 * 参数:    ids: 1474391993,1474391997,1474391996
	 * 返回值:  系统返回值VO
	 * List 可以赋值 name="list[0]" value=100
	 * 				name="list[1]" value=200
	 */
	@RequestMapping("/delete")
	public SysResult deleteItems(Long[] ids){

		itemService.deleteItems(ids);
		return SysResult.success();
	}

	/**
	 * 实现商品的下架
	 * url地址: http://localhost:8091/item/updateStatus/2    status=2
	 * 			http://localhost:8091/item/updateStatus/1   status=1
	 * 利用RestFul风格实现通用的操作.
	 * 参数:   ids: 1474391997,1474391996,1474391995
	 * 返回值:  VO对象
	 */
	@RequestMapping("/updateStatus/{status}")
	public SysResult updateStatus(@PathVariable Integer status,Long[] ids){

		itemService.updateStatus(status,ids);
		return SysResult.success();
	}

	/**
	 * 业务:动态获取商品详情信息
	 * url地址: http://localhost:8091/item/query/item/desc/1474391999
	 * 参数:   itemId restFul方式获取
	 * 返回值: 系统VO对象
	 */
	@RequestMapping("/query/item/desc/{itemId}")
	public SysResult findItemDescById(@PathVariable Long itemId){

		ItemDesc itemDesc = itemService.findItemDescById(itemId);
		return SysResult.success(itemDesc);
	}





}
