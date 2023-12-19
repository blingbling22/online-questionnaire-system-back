package com.back.black.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.List;
// 问题，

//问题

@Data
@TableName("question")
public class Question {
    @TableId(type = IdType.AUTO)
    private Integer id;//id

    @NotEmpty
    @TableField("type")
    private int type;//1: 单选 2：多选  3：简答题

    @TableField("paper_id")
    private Integer paperId;//所属的试卷id

    @NotEmpty
    @TableField("title")
    private String title;//题目的标题

    @URL
    @TableField("pic_url")
    private List<String> picUrl;//题目附带的图片

    //    private List<Operation> operations;//选项

    @TableField("score")
    private double score;//分值

    @TableField("right_answer")
    private String rightAnswer;//正确答案 格式 ：单选 "A"，多选 "ABD"

    @TableField(exist = false,select = false)
    private List<Option> options;
    //构造函数
    public Question(Integer id, int type, Integer paperId, String title, List<String> picUrl, double score, String rightAnswer) {
        this.id = id;
        this.type = type;
        this.paperId = paperId;
        this.title = title;
        this.picUrl = picUrl;
        this.score = score;
        this.rightAnswer = rightAnswer;
    }

    public Question(Integer id, int type, Integer paperId, String title, List<String> picUrl, double score, String rightAnswer, List<Option> options) {
        this.id = id;
        this.type = type;
        this.paperId = paperId;
        this.title = title;
        this.picUrl = picUrl;
        this.score = score;
        this.rightAnswer = rightAnswer;
        this.options = options;
    }
    public Question(){}
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