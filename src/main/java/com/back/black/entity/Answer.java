package com.back.black.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("answer")
public class Answer {
    @TableId(type = IdType.AUTO)
    private Integer id;//id

    @TableField("answer_content")
    private String answer_content;//回答文字内容

    @TableField("question_id")
    private Integer question_id;//

    @TableField("paper_id")
    private Integer paper_id;//

    @TableField("selection")
    private String selection;//选项的id  多选就"102,104" 102 104为选项在数据库的id  问答就为空

    @TableField("question_type")
    private Integer question_type;//问题类型 1 2 3

    //下面的转化为selection
    @TableField(exist=false,select = false)
    private List<Integer> option_ids;

    @TableField(exist=false,select = false)
    private Integer option_id;
    // answer.value = [
//     {
//         option_id: '', //单选
//         option_ids: [], // 多选
//         answer_content: '', //问答
//         question_id: '',
//         paper_id: '',
//         question_type: ''
//     }
// ]
    public Answer(){}

    public Answer(Integer id, String answer_content, Integer question_id, Integer paper_id, String selection, Integer question_type) {
        this.id = id;
        this.answer_content = answer_content;
        this.question_id = question_id;
        this.paper_id = paper_id;
        this.selection = selection;
        this.question_type = question_type;
    }

    public Answer(Integer id, String answer_content, Integer question_id, Integer paper_id, String selection, Integer question_type, List<Integer> option_ids, Integer option_id) {
        this.id = id;
        this.answer_content = answer_content;
        this.question_id = question_id;
        this.paper_id = paper_id;
        this.selection = selection;
        this.question_type = question_type;
        this.option_ids = option_ids;
        this.option_id = option_id;
    }
}
