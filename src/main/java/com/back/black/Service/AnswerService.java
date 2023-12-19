package com.back.black.Service;

import com.back.black.entity.Answer;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnswerService extends IService<Answer> {
    void insert(Answer answer);
    void insert(List<Answer> answers);

}
