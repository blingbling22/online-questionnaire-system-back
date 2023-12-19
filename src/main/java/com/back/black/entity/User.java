package com.back.black.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@TableName("user")
public class User {
    //安照数据库的顺序
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("username")
    private String username;
    @TableField("email")
    private String email;
    @TableField("password")
    private String password;
}
//@TableName("user")  //对应的表名
//public class User {
//    @TableId(type = IdType.AUTO)   //对应的主键
//    int id;
//    @TableField("name")   //对应的字段
//    String name;
//    @TableField("email")
//    String email;
//    @TableField("password")
//    String password;
//}