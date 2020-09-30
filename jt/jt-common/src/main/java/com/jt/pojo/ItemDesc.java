package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@TableName("tb_item_desc")
@Data
@Accessors(chain = true)
public class ItemDesc extends BasePojo{

    //item中的id与ItemDesc中的Id应该保持一致...
    @TableId    //只标识主键  不能自增.
    private Long itemId;
    private String itemDesc;
}
