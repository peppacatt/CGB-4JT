package com.jt.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = -2293941719496730677L;
    private Integer id;
    private String name;
    private Integer age;
    private String sex;

}
