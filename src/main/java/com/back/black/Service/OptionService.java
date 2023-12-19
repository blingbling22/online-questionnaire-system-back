package com.back.black.Service;

import com.back.black.entity.Option;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface OptionService extends IService<Option> {
    void insertOpt(Option option);

    List<Option> getByQuestionId(Integer questionId);

    Option getById(Integer id);
}
