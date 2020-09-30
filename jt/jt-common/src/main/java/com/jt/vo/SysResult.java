package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

//SysResult 主要的目的是为了与页面进行交互.  ajax/json
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SysResult {

    private Integer status;  //200成功   201 失败
    private String  msg;     //服务器提示信息   成功  失败
    private Object  data;    //服务器返回值数据.

    //可以利用static的静态方法 将数据动态返回
    public static SysResult fail(){

        return new SysResult(201, "业务执行失败", null);
    }

    /**
     *  1.只需要返回状态码信息   200
     *  2.需要返状态及业务数据   200/data
     *  3.返回提示信息/data业务数据
     * @return
     */
    public static SysResult success(){

        return new SysResult(200, "业务执行成功!", null);
    }
    //String json = "{key:value}"
    public static SysResult success(Object data){

        return new SysResult(200, "业务执行成功!", data);
    }

    //只想返回提示信息
    public static SysResult success(String msg,Object data){

        return new SysResult(200, msg, data);
    }
}
