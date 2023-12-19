package com.back.black.Service.Impl;

import com.back.black.Mapper.AnswerMapper;
import com.back.black.Mapper.PaperMapper;
import com.back.black.Service.AnswerService;
import com.back.black.Service.PaperService;
import com.back.black.entity.Answer;
import com.back.black.entity.Paper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerImpl extends ServiceImpl<AnswerMapper, Answer>
        implements AnswerService {
    @Resource
    AnswerMapper mapper;
    @Override
    public void insert(Answer answer) {
        int type = answer.getQuestion_type();
        if(type == 3) {
            answer.setSelection(answer.getAnswer_content());

        }
        else if(type == 1){
            answer.setSelection(answer.getOption_id().toString());
        } else if (type == 2) {
            answer.setSelection(answer.getOption_ids().toString());
        }
        mapper.insert(answer);

    }

    @Override
    public void insert(List<Answer> answers) {
        for (Answer answer:answers){
            answer.setId(null);
            this.insert(answer);
        }
    }
}
