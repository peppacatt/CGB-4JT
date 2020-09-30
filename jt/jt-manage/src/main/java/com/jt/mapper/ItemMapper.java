package com.jt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.Item;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ItemMapper extends BaseMapper<Item>{

    @Select("SELECT * FROM tb_item ORDER BY updated DESC LIMIT #{startIndex},#{rows}")
    List<Item> findItemByPage(Integer startIndex, Integer rows);
}
