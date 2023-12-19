package com.back.black.Service.Impl;

import com.back.black.Mapper.QuestionMapper;
import com.back.black.Service.QuestionService;
import com.back.black.entity.Question;
import com.back.black.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionImpl extends ServiceImpl<QuestionMapper, Question>
        implements QuestionService {
    @Resource
    QuestionMapper mapper;
    @Override
    public Question getById(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public List<Question> findQuestionByPaperId(Integer paper_id) {
        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        wrapper.eq("paper_id", paper_id);
        return mapper.selectList(wrapper);
    }

    @Override
    public void insertQuestion(Question question) {
        mapper.insert(question);
    }


}
