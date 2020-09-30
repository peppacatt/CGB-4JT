package com.jt.aop;

import com.jt.vo.SysResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

//@ControllerAdvice  //拦截controller层
//@ResponseBody
@RestControllerAdvice   //定义全局异常的处理类   AOP=异常通知
public class SystemAOP {

    /**
     *  定义全局异常的方法  当遇到了什么异常时,程序开始执行   参数一般class类型
     *  如果一旦发生异常,则应该输出异常的信息,之后返回错误数据即可.
     */
    @ExceptionHandler({RuntimeException.class})
    public Object systemAop(Exception e){
        e.printStackTrace();
        return SysResult.fail();
    }
}
