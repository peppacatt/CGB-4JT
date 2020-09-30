package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class EasyUITree implements Serializable {

    private Long id;        //节点ID信息
    private String text;    //节点的名称
    private String state;   //节点的状态   open 打开  closed 关闭

}
