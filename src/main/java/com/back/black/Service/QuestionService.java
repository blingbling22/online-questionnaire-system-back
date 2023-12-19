package com.back.black.Service;

import com.back.black.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService extends IService<Question> {

    Question getById(Integer id);
    List<Question> findQuestionByPaperId(Integer paper_id);

    void insertQuestion(Question question);


}
