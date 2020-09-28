package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public ItemCat findItemCatById(Long itemCatId) {

        return itemCatMapper.selectById(itemCatId);
    }

    /**
     * 返回值:  List<EasyUITree> 集合信息
     * 数据库查询返回值:  List<ItemCat>
     * 数据类型必须手动的转化
     * @param parentId
     * @return
     */
    @Override
    public List<EasyUITree> findItemCatList(Long parentId) {
        //1.查询数据库记录
        QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);
        List<ItemCat> catList = itemCatMapper.selectList(queryWrapper);

        //2.需要将catList集合转化为voList  一个一个转化
        List<EasyUITree> treeList = new ArrayList<>();
        for(ItemCat itemCat :catList){
            Long id = itemCat.getId();
            String name = itemCat.getName();
            //如果是父级 应该closed   如果不是父级 应该open
            String state = itemCat.getIsParent()?"closed":"open";
            EasyUITree tree = new EasyUITree(id, name, state);
            treeList.add(tree);
        }
        return treeList;
    }
}
