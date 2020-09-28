package com.jt.controller;

import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import com.jt.vo.EasyUITree;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    /**
     * url地址:/item/cat/queryItemName
     * http://localhost:8091/item/cat/queryItemName?itemCatId=457
     * 参数: {itemCatId:val}
     * 返回值: 商品分类名称
     */
    @RequestMapping("/queryItemName")
    public String findItemCatNameById(Long itemCatId){

        //根据商品分类Id查询商品分类对象
        ItemCat itemCat = itemCatService.findItemCatById(itemCatId);
        return itemCat.getName();   //返回商品分类的名称
    }


    /**
     * 业务需求: 用户通过ajax请求,动态获取树形结构的数据.
     * url:  http://localhost:8091/item/cat/list
     * 参数: 只查询一级商品分类信息   parentId = 0
     * 返回值结果:  List<EasyUITree>
     *
     *  注意事项:
     *          1.树形结构初始化时不会传递任何信息.只有展开子节点时传递Id
     *          2.页面传递什么样的数据,后端必须接收什么样的数据
     *
     *  id: 296
     */


    /**
     * SpringMVC 参数获取的过程.....
     * 页面参数:  id: 296  name=xxxx
     * mvc参数:   xxx(Long id,String name);
     * @param parentId
     * @return
     */
    //2.一般写业务需要见名知意
    @RequestMapping("/list")
    public List<EasyUITree> findItemCatList(
            @RequestParam(value = "id",defaultValue = "0") Long parentId){

        //方式1
        //Long parentId = (id==null?0L:id);
        return itemCatService.findItemCatList(parentId);
    }







}