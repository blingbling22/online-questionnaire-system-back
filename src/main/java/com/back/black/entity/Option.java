package com.back.black.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@TableName("option2")
public class Option {
    @TableId(type = IdType.AUTO)
    private Integer id;//id

    @TableField("question_id")
    private Integer question_id;//标题

    @TableField("content")
    private String content;

    @TableField("pic_url")
    private String pic_url;

}
