package com.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

	/*@RequestMapping("/index")
	public String index(){

		return "index";
	}*/

	/**
	 * 业务需求:
	 * 		实现用户页面的跳转
	 * 	url:  http://localhost:8091/page/item-add    页面:item-add
	 * 		  http://localhost:8091/page/item-list	 页面:item-list
	 *
	 * 	能否利用一个方法实现通用页面的跳转功能!!!!
	 *  实现的思路: 如果能够动态的获取url中的参数就可以实现页面的跳转. restFul风格....
	 *  restFul语法:
	 *  	1. 参数必须使用"/"分隔
	 *  	2. 参数必须使用{}形式包裹
	 *  	3. 参数接收时需要使用 @PathVariable 获取
	 *
	 *  restFul风格用法2:
	 *  	利用不同的请求的类型,定义不同的业务功能!!
	 *  	type="GET",   	查询业务
	 *  	type="POST",  	新增操作
	 *  	type="PUT",	  	更新操作
	 *  	type="DELETE" 	删除操作
	 * @return
	 */

	//@RequestMapping(value = "/page/{moduleName}",method = RequestMethod.GET)\
	/*@PutMapping
	@DeleteMapping
	@PostMapping*/
	@GetMapping("/page/{moduleName}")
	public String module(@PathVariable String moduleName) {
		
		return moduleName;
	}
}
