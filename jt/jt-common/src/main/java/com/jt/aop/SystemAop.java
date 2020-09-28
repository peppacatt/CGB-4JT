package com.jt.aop;

import com.jt.vo.SysResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

//@ControllerAdvice   //拦截controller层
@RestControllerAdvice
public class SystemAop {

    @ExceptionHandler({RuntimeException.class, SQLException.class})
    public Object systemAop(Exception e){
        e.printStackTrace();
        return SysResult.fail();
    }

}
