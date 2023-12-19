package com.back.black.Service.Impl;

import com.back.black.Mapper.test2Mapper;
import com.back.black.Service.OptionService;
import com.back.black.entity.Option;
import com.back.black.entity.Question;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionImpl extends ServiceImpl<test2Mapper, Option>
        implements OptionService {
@Resource
test2Mapper mapper;

    @Override
    public void insertOpt(Option Option) {
        mapper.insert(Option);
    }

    @Override
    public List<Option> getByQuestionId(Integer questionId) {
        QueryWrapper<Option> wrapper = new QueryWrapper<>();
        wrapper.eq("question_id", questionId);
        return mapper.selectList(wrapper);
    }

    @Override
    public Option getById(Integer id) {
        return mapper.selectById(id);
    }
}
