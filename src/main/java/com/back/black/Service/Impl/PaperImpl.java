package com.back.black.Service.Impl;

import com.back.black.Mapper.PaperMapper;
import com.back.black.Service.OptionService;
import com.back.black.Service.PaperService;
import com.back.black.Service.QuestionService;
import com.back.black.entity.Option;
import com.back.black.entity.Paper;
import com.back.black.entity.Question;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PaperImpl extends ServiceImpl<PaperMapper, Paper>
        implements PaperService {

    @Resource
    PaperMapper mapper;
    @Resource
    QuestionService questionService;
    @Resource
    OptionService optionService;

    @Override
    public Integer insertPaper(Paper paper) {

        List<Question> questionList = paper.getQuestions();
        paper.setId(null);//不然插入不进去，主键冲突
        paper.setQuestions(null);//同理

        mapper.insert(paper);
//        System.out.println("id:"+paper.getId());
        int paperId = paper.getId();//得到返回的paper id

        for (Question question : questionList) {
            question.setPaperId(paperId);
            List<Option> options = question.getOptions();
            question.setOptions(null);
            question.setId(null);//不然插入不进去，主键冲突
            //插入Question
            questionService.insertQuestion(question);
            int questionId = question.getId();
//            System.out.println("questionId:"+questionId);
            //先插入question中的选项
            if (question.getType() == 1 || question.getType() == 2) {
                if (options != null) {
                    for (Option option : options) {
//                    System.out.println("option.setQuestion_id"+questionId);
                        option.setQuestion_id(questionId);
                        option.setId(null);
                        optionService.insertOpt(option);
                    }

                } else {
                    log.info("questionId:"+questionId+",类型为选择但没有选项");
                }
            }
        }
//            else if (question.getType() == 3) {
//                System.out.println();
//            }
        return paper.getId();
    }



    @Override
    public Paper getPaperById(Integer id) {
        Paper paper = mapper.selectById(id);
        if(paper==null) return null;
        System.out.println(paper.getEnd_time());
        System.out.println(paper.getTotal_score());

        List<Question> questions = questionService.findQuestionByPaperId(paper.getId());
        for(Question question : questions){
            //类型为单选或多选
            if (question.getType() == 1 || question.getType() == 2){
                List<Option> options = optionService.getByQuestionId(question.getId());
                question.setOptions(options);//添加options到question
            }

        }
        //添加question到 paper
        paper.setQuestions(questions);


        return paper;
    }
}
