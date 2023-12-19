package com.back.black.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@TableName("paper")
//@AllArgsConstructor
@Data
public class Paper {
    @TableId(type = IdType.AUTO)
    private Integer id;//id

    @TableField("title")
    private String title;//标题

    @TableField("description")
    private String description;//对试卷的一些说明

    @TableField("creator_id")
    private Integer creatorId;//创建用户的id

    //    private List<Question> questions;//问题列表
    @TableField("status")
    private String status;//试卷状态 创建||发布||终止

    @TableField("create_time")
    private LocalDateTime create_time;//发布时间

    @TableField("end_time")
    private LocalDateTime end_time;//最后提交时间

    @TableField("start_time")
    private LocalDateTime start_time;//开始答题时间

    @TableField("total_score")
    private double total_score;//分数

    @TableField("time_limit")
    private int time_limit;//时间

    @TableField(exist=false,select = false)
    private List<Question> questions;

    Paper(){}

    public Paper(Integer id, String title, String description, Integer creatorId, String status, LocalDateTime create_time, LocalDateTime end_time, LocalDateTime start_time, double total_score, int time_limit, List<Question> questions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creatorId = creatorId;
        this.status = status;
        this.create_time = create_time;
        this.end_time = end_time;
        this.start_time = start_time;
        this.total_score = total_score;
        this.time_limit = time_limit;
        this.questions = questions;
    }

    public Paper(Integer id, String title, String description, Integer creatorId, String status, LocalDateTime create_time, LocalDateTime end_time, LocalDateTime start_time, double total_score, int time_limit) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creatorId = creatorId;
        this.status = status;
        this.create_time = create_time;
        this.end_time = end_time;
        this.start_time = start_time;
        this.total_score = total_score;
        this.time_limit = time_limit;
    }


}
//@Data
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